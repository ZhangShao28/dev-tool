package cn.moonnow.tool.persistent.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import cn.moonnow.tool.entity.Columns;
import cn.moonnow.tool.entity.Dt;
import cn.moonnow.tool.entity.Pk;
import cn.moonnow.tool.entity.Sort;
import cn.moonnow.tool.entity.VirtualColumns;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * jdbc封装
 */
@Log4j2
public abstract class ToolPersistent {

  public static final String LOG = "ToolPersistent";

  protected JdbcTemplate jdbcTemplate;

  protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @javax.annotation.Resource
  public void setDataSource(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  public static final String ASC = "ASC";
  public static final String DESC = "DESC";

  @Value("${spring.datasource.druid.driver-class-name}")
  private String dataBaseType;

  public static final String MYSQL = "com.mysql.cj.jdbc.Driver";
  public static final String ORACLE = "oracle.jdbc.OracleDriver";

  protected static final StringBuilder getInsertSql(String tableName, Set<String> columns, Map<String, String> columnsParameter) {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + tableName);
      log.debug(ToolUtil.LOG + columns);
      log.debug(ToolUtil.LOG + columnsParameter);
    }
    StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" ( ");
    StringBuilder value = new StringBuilder();
    boolean isFirst = true;
    for (String column : columns) {
      if (isFirst) {
        isFirst = false;
      } else {
        sql.append(", ");
        value.append(", ");
      }
      sql.append(columnsParameter.get(column));
      value.append(":").append(column);
    }
    sql.append(" ) VALUES ( ");
    sql.append(value);
    sql.append(" )");
    if (log.isInfoEnabled()) {
      log.info(tableName + " insert sql is : " + sql.toString());
    }
    return sql;
  }

  protected static final StringBuilder getUpdateSql(String tableName, Set<String> columns, Map<String, String> columnsParameter, Set<String> primaryKey) {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + tableName);
      log.debug(ToolUtil.LOG + columns);
      log.debug(ToolUtil.LOG + columnsParameter);
      log.debug(ToolUtil.LOG + primaryKey);
    }
    StringBuilder sql = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
    boolean isFirst = true;
    for (String column : columns) {
      if (isFirst) {
        isFirst = false;
      } else {
        sql.append(", ");
      }
      sql.append(columnsParameter.get(column)).append(" = :").append(column);
    }
    if (ToolUtil.isNotEmpty(primaryKey)) {
      sql.append(" WHERE ");
      isFirst = true;
      for (String pk : primaryKey) {
        if (isFirst) {
          isFirst = false;
        } else {
          sql.append(" AND ");
        }
        sql.append(columnsParameter.get(pk)).append(" = :").append(pk);
      }
    }
    if (log.isInfoEnabled()) {
      log.info(tableName + " update sql is : " + sql.toString());
    }
    return sql;
  }

  protected static final StringBuilder getDelSql(String tableName, Map<String, String> columnsParameter, Set<String> primaryKey) {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + tableName);
      log.debug(ToolUtil.LOG + columnsParameter);
      log.debug(ToolUtil.LOG + primaryKey);
    }
    StringBuilder sql = new StringBuilder("DELETE FROM ").append(tableName).append(" WHERE ");
    boolean isFirst = true;
    if (ToolUtil.isNotEmpty(primaryKey)) {
      for (String pk : primaryKey) {
        if (isFirst) {
          isFirst = false;
        } else {
          sql.append(" AND ");
        }
        sql.append(columnsParameter.get(pk)).append(" = :").append(pk);
      }
    }
    if (log.isInfoEnabled()) {
      log.info(tableName + " del sql is : " + sql.toString());
    }
    return sql;
  }

  protected static final StringBuilder getSelectSql(String tableName, Set<String> columns, Map<String, String> columnsParameter, Set<String> primaryKey) {
    return getSelectSql(tableName, columns, columnsParameter, null, primaryKey, null);
  }

  protected static final StringBuilder getSelectSql(String tableName, Set<String> columns, Map<String, String> columnsParameter, Set<String> primaryKey, String alias) {
    return getSelectSql(tableName, columns, columnsParameter, null, primaryKey, alias);
  }

  protected static final StringBuilder getSelectSql(String tableName, Set<String> columns, Map<String, String> columnsParameter, Set<String> virtualColumns, Set<String> primaryKey) {
    return getSelectSql(tableName, columns, columnsParameter, virtualColumns, primaryKey, null);
  }

  protected static final StringBuilder getSelectSql(String tableName, Set<String> columns, Map<String, String> columnsParameter, Set<String> virtualColumns, Set<String> primaryKey, String alias) {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + tableName);
      log.debug(ToolUtil.LOG + columns);
      log.debug(ToolUtil.LOG + columnsParameter);
      log.debug(ToolUtil.LOG + virtualColumns);
      log.debug(ToolUtil.LOG + primaryKey);
      log.debug(ToolUtil.LOG + alias);
    }
    StringBuilder sql = new StringBuilder("SELECT ");
    boolean isFirst = true;
    for (String column : columns) {
      if (isFirst) {
        isFirst = false;
      } else {
        sql.append(", ");
      }
      if (ToolUtil.isNotNullStr(alias)) {
        sql.append(alias).append(".");
      }
      sql.append(columnsParameter.get(column));
    }
    if (ToolUtil.isNotEmpty(virtualColumns)) {
      isFirst = true;
      for (String virtualColumn : virtualColumns) {
        if (sql.length() > 7) {
          sql.append(", ");
        } else {
          if (isFirst) {
            isFirst = false;
          } else {
            sql.append(", ");
          }
        }
        sql.append(virtualColumn);
      }
    }
    sql.append(" FROM ").append(tableName);
    if (ToolUtil.isNotNullStr(alias)) {
      sql.append(" ").append(alias);
    }
    if (ToolUtil.isNotEmpty(primaryKey)) {
      isFirst = true;
      for (String column : primaryKey) {
        if (isFirst) {
          sql.append(" WHERE ");
          isFirst = false;
        } else {
          sql.append(" AND ");
        }
        if (ToolUtil.isNotNullStr(alias)) {
          sql.append(alias).append(".");
        }
        sql.append(columnsParameter.get(column)).append(" IS NOT NULL");
      }
    }
    if (log.isInfoEnabled()) {
      log.info(tableName + " select sql is : " + sql.toString());
    }
    return sql;
  }

  protected static final StringBuilder getCountSql(String tableName, Map<String, String> columnsParameter, Set<String> primaryKey) {
    return getCountSql(tableName, columnsParameter, primaryKey, null);
  }

  protected static final StringBuilder getCountSql(String tableName, Map<String, String> columnsParameter, Set<String> primaryKey, String alias) {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + tableName);
      log.debug(ToolUtil.LOG + columnsParameter);
      log.debug(ToolUtil.LOG + primaryKey);
      log.debug(ToolUtil.LOG + alias);
    }
    StringBuilder sql = new StringBuilder("SELECT ");
    if (ToolUtil.isNotEmpty(primaryKey) && primaryKey.size() == 1) {
      sql.append("count(");
      if (ToolUtil.isNotNullStr(alias)) {
        sql.append(alias).append(".");
      }
      sql.append(columnsParameter.get(primaryKey.iterator().next()));
      sql.append(") AS COUNTS");
    } else {
      sql.append("count(*) AS COUNTS");
    }
    sql.append(" FROM ").append(tableName);
    if (ToolUtil.isNotNullStr(alias)) {
      sql.append(" ").append(alias);
    }
    if (ToolUtil.isNotEmpty(primaryKey)) {
      boolean isFirst = true;
      for (String column : primaryKey) {
        if (isFirst) {
          sql.append(" WHERE ");
          isFirst = false;
        } else {
          sql.append(" AND ");
        }
        if (ToolUtil.isNotNullStr(alias)) {
          sql.append(alias).append(".");
        }
        sql.append(columnsParameter.get(column)).append(" IS NOT NULL");
      }
    }
    if (log.isInfoEnabled()) {
      log.info(tableName + " count sql is : " + sql.toString());
    }
    return sql;
  }

  protected StringBuilder getSortSql(Map<String, String> sort, Map<String, String> columnsParameter, String alias) {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sort);
      log.debug(ToolUtil.LOG + columnsParameter);
      log.debug(ToolUtil.LOG + alias);
    }
    if (ToolUtil.isEmpty(sort)) {
      return new StringBuilder("");
    }
    StringBuilder sql = new StringBuilder(" ORDER BY ");
    boolean isFirst = true;
    for (Map.Entry<String, String> entry : sort.entrySet()) {
      if (isFirst) {
        isFirst = false;
      } else {
        sql.append(", ");
      }
      if (ToolUtil.isNotNullStr(alias)) {
        sql.append(alias).append(".");
      }
      sql.append(columnsParameter.get(entry.getKey())).append(" ").append(entry.getValue());
    }
    if (log.isInfoEnabled()) {
      log.info(alias + " sort sql is : " + sql.toString());
    }
    return sql;
  }

  protected <T extends Serializable> StringBuilder getQuerySql(Set<String> columns, Map<String, String> columnsParameter, String alias, T o) {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columns);
      log.debug(ToolUtil.LOG + columnsParameter);
      log.debug(ToolUtil.LOG + alias);
      log.debug(ToolUtil.LOG + o);
    }
    StringBuilder sql = new StringBuilder();
    try {
      Class<?> oClass = o.getClass();
      LinkedHashSet<Field> tFields = new LinkedHashSet<Field>();
      LinkedHashSet<Method> getMethods = new LinkedHashSet<Method>();
      while (oClass != null) {
        tFields.addAll(Arrays.asList(oClass.getDeclaredFields()));
        getMethods.addAll(Arrays.asList(oClass.getMethods()));
        oClass = oClass.getSuperclass();
      }
      if (tFields != null && tFields.size() >= 1) {
        for (Field tField : tFields) {
          // 根据成员变量名定义get方法名
          String getMethodStr = "get" + tField.getName().substring(0, 1).toUpperCase() + tField.getName().substring(1);
          // 标识成员变量是否有get方法
          boolean isGetMethodStr = false;
          // 目标对象成员变量有get方法 改变isGetMethodStr变量的值为true
          for (Method getMethod : getMethods) {
            if (getMethodStr.equals(getMethod.getName())) {
              isGetMethodStr = true;
              break;
            }
          }
          // 目标对象成员变量有get方法
          if (isGetMethodStr) {
            Method tM = o.getClass().getMethod(getMethodStr);
            // 字段有值才进行sql拼接
            if (tM.invoke(o) != null) {
              // 先进行多对多关系sql拼接
              if ("toolExists".equals(tField.getName())) {
                sql.append(" AND EXISTS ( ").append((String) tM.invoke(o)).append(" )");
              } else if ("toolNotExists".equals(tField.getName())) {
                sql.append(" AND NOT EXISTS ( ").append((String) tM.invoke(o)).append(" )");
              } else {
                // 确定哪个列和那种拼接sql方式
                for (String column : columns) {
                  if (tField.getName().indexOf(column) == 0) {
                    if (tField.getName().equals(column)) {
                      sql.append(" AND ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" = :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.AND_EQ)) {
                      sql.append(" AND ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" = :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.AND_LIKE)) {
                      sql.append(" AND ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" LIKE :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.AND_IN)) {
                      sql.append(" AND ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" IN ( :").append(tField.getName()).append(" )");
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.AND_KEY_LIKE)) {
                      sql.append(" AND ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" LIKE :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.OR_KEY_LIKE)) {
                      sql.append(" OR ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" LIKE :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.AND_NE)) {
                      sql.append(" AND ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" <> :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.AND_NIN)) {
                      sql.append(" AND ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" NOT IN ( :").append(tField.getName()).append(" )");
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.AND_G)) {
                      sql.append(" AND ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" > :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.AND_L)) {
                      sql.append(" AND ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" < :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.AND_GE)) {
                      sql.append(" AND ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" >= :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.AND_LE)) {
                      sql.append(" AND ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" <= :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.OR_EQ)) {
                      sql.append(" OR ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" = :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.OR_LIKE)) {
                      sql.append(" OR ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" LIKE :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.OR_IN)) {
                      sql.append(" OR ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" IN ( :").append(tField.getName()).append(" )");
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.OR_NE)) {
                      sql.append(" OR ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" <> :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.OR_NIN)) {
                      sql.append(" OR ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" NOT IN ( :").append(tField.getName()).append(" )");
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.OR_G)) {
                      sql.append(" OR ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" > :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.OR_L)) {
                      sql.append(" OR ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" < :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.OR_GE)) {
                      sql.append(" OR ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" >= :").append(tField.getName());
                      break;
                    } else if (tField.getName().equals(column + ToolUtil.OR_LE)) {
                      sql.append(" OR ");
                      if (ToolUtil.isNotNullStr(alias)) {
                        sql.append(alias).append(".");
                      }
                      sql.append(columnsParameter.get(column)).append(" <= :").append(tField.getName());
                      break;
                    }
                  }
                }
              }
            }
          }
        }
      }
    } catch (SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return sql;
  }

  protected StringBuilder getPagingSql(String tableName, Map<String, String> columnsParameter, Set<String> primaryKey, String alias) {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + tableName);
      log.debug(ToolUtil.LOG + columnsParameter);
      log.debug(ToolUtil.LOG + primaryKey);
      log.debug(ToolUtil.LOG + alias);
    }
    StringBuilder sql = new StringBuilder("SELECT ");
    if (MYSQL.equals(dataBaseType)) {
      if (ToolUtil.isNotEmpty(primaryKey)) {
        boolean isFirst = true;
        for (String pk : primaryKey) {
          if (isFirst) {
            isFirst = false;
          } else {
            sql.append(", ");
          }
          if (ToolUtil.isNotNullStr(alias)) {
            sql.append(alias).append(".");
          }
          sql.append(columnsParameter.get(pk));
        }
      }
      sql.append(" FROM ").append(tableName);
      if (ToolUtil.isNotNullStr(alias)) {
        sql.append(" ").append(alias);
      }
      if (ToolUtil.isNotEmpty(primaryKey)) {
        boolean isFirst = true;
        for (String pk : primaryKey) {
          if (isFirst) {
            sql.append(" WHERE ");
            isFirst = false;
          } else {
            sql.append(" AND ");
          }
          if (ToolUtil.isNotNullStr(alias)) {
            sql.append(alias).append(".");
          }
          sql.append(columnsParameter.get(pk)).append(" IS NOT NULL");
        }
      }
    } else if (ORACLE.equals(dataBaseType)) {
      if (ToolUtil.isNotEmpty(primaryKey)) {
        boolean isFirst = true;
        for (String pk : primaryKey) {
          if (isFirst) {
            isFirst = false;
          } else {
            sql.append(", ");
          }
          sql.append(columnsParameter.get(pk));
        }
      }
      sql.append(" FROM ( SELECT ROWNUM RN, ");
      if (ToolUtil.isNotEmpty(primaryKey)) {
        boolean isFirst = true;
        for (String pk : primaryKey) {
          if (isFirst) {
            isFirst = false;
          } else {
            sql.append(", ");
          }
          sql.append("alias.");
          sql.append(columnsParameter.get(pk));
        }
      }
      sql.append(" FROM ( SELECT ");
      if (ToolUtil.isNotEmpty(primaryKey)) {
        boolean isFirst = true;
        for (String pk : primaryKey) {
          if (isFirst) {
            isFirst = false;
          } else {
            sql.append(", ");
          }
          if (ToolUtil.isNotNullStr(alias)) {
            sql.append(alias).append(".");
          }
          sql.append(columnsParameter.get(pk));
        }
      }
      sql.append(" FROM ").append(tableName);
      if (ToolUtil.isNotNullStr(alias)) {
        sql.append(" ").append(alias);
      }
      if (ToolUtil.isNotEmpty(primaryKey)) {
        boolean isFirst = true;
        for (String pk : primaryKey) {
          if (isFirst) {
            sql.append(" WHERE ");
            isFirst = false;
          } else {
            sql.append(" AND ");
          }
          if (ToolUtil.isNotNullStr(alias)) {
            sql.append(alias).append(".");
          }
          sql.append(columnsParameter.get(pk)).append(" IS NOT NULL");
        }
      }
    }
    if (log.isInfoEnabled()) {
      log.info(tableName + " paging sql is : " + sql.toString());
    }
    return sql;
  }

  protected <T> StringBuilder getPagingSql(Map<String, String> sort, Map<String, String> columnsParameter, String alias, Paging<T> paging) {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sort);
      log.debug(ToolUtil.LOG + columnsParameter);
      log.debug(ToolUtil.LOG + alias);
      log.debug(ToolUtil.LOG + paging);
    }
    StringBuilder sql = new StringBuilder();
    if (MYSQL.equals(dataBaseType)) {
      if (ToolUtil.isNotEmpty(sort)) {
        sql.append(this.getSortSql(sort, columnsParameter, alias));
      }
      sql.append(" LIMIT ").append(paging.getOffset().toString()).append(", ").append(paging.getRows().toString());
    } else if (ORACLE.equals(dataBaseType)) {
      if (ToolUtil.isNotEmpty(sort)) {
        sql.append(this.getSortSql(sort, columnsParameter, alias));
      }
      sql.append(" ) alias WHERE ROWNUM <= ").append(paging.getEndOffset().toString());
      sql.append(" ) WHERE RN >= ").append(paging.getStartOffset().toString());
    }
    if (log.isInfoEnabled()) {
      log.info(alias + " paging sql is : " + sql.toString());
    }
    return sql;
  }

  protected StringBuilder getByPkSql(Map<String, String> columnsParameter, Set<String> primaryKey, String alias) {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columnsParameter);
      log.debug(ToolUtil.LOG + primaryKey);
      log.debug(ToolUtil.LOG + alias);
    }
    StringBuilder sql = new StringBuilder(" AND ");
    boolean isFirst = true;
    if (ToolUtil.isNotEmpty(primaryKey)) {
      for (String pk : primaryKey) {
        if (isFirst) {
          isFirst = false;
        } else {
          sql.append(" AND ");
        }
        if (ToolUtil.isNotNullStr(alias)) {
          sql.append(alias).append(".");
        }
        sql.append(columnsParameter.get(pk)).append(" = :").append(pk);
      }
    }
    if (log.isInfoEnabled()) {
      log.info(alias + " getByPk sql is : " + sql.toString());
    }
    return sql;
  }

  protected <T extends Serializable> void insert(StringBuilder insertSql, T o) {
    this.namedParameterJdbcTemplate.update(insertSql.toString(), new BeanPropertySqlParameterSource(o));
  }

  protected <T extends Serializable> void insert(StringBuilder insertSql, Collection<T> oSet) {
    this.namedParameterJdbcTemplate.batchUpdate(insertSql.toString(), SqlParameterSourceUtils.createBatch(oSet.toArray()));
  }

  protected <T extends Serializable> void update(StringBuilder updateSql, T o) {
    this.namedParameterJdbcTemplate.update(updateSql.toString(), new BeanPropertySqlParameterSource(o));
  }

  protected <T extends Serializable> void update(StringBuilder updateSql, Collection<T> oSet) {
    this.namedParameterJdbcTemplate.batchUpdate(updateSql.toString(), SqlParameterSourceUtils.createBatch(oSet.toArray()));
  }

  protected void del(StringBuilder delSql, MapSqlParameterSource mapSqlParameterSource) {
    this.namedParameterJdbcTemplate.update(delSql.toString(), mapSqlParameterSource);
  }

  protected <T extends Serializable> void del(StringBuilder delSql, Collection<T> oSet) {
    this.namedParameterJdbcTemplate.batchUpdate(delSql.toString(), SqlParameterSourceUtils.createBatch(oSet.toArray()));
  }

  protected <T extends Serializable> Collection<T> query(StringBuilder querySql, MapSqlParameterSource mapSqlParameterSource, Class<T> entityClass) {
    return this.namedParameterJdbcTemplate.query(querySql.toString(), mapSqlParameterSource, BeanPropertyRowMapper.newInstance(entityClass));
  }

  protected <T extends Serializable> Collection<T> query(StringBuilder querySql, Class<T> entityClass) {
    return this.namedParameterJdbcTemplate.query(querySql.toString(), new MapSqlParameterSource(), BeanPropertyRowMapper.newInstance(entityClass));
  }

  protected <T extends Serializable, R extends Serializable> Collection<R> query(StringBuilder querySql, T o, Class<R> entityClass) {
    return this.namedParameterJdbcTemplate.query(querySql.toString(), new BeanPropertySqlParameterSource(o), BeanPropertyRowMapper.newInstance(entityClass));
  }

  protected Long queryCount(StringBuilder querySql, MapSqlParameterSource mapSqlParameterSource, Class<Long> longClass) {
    return this.namedParameterJdbcTemplate.queryForObject(querySql.toString(), mapSqlParameterSource, longClass);
  }

  protected <T extends Serializable> Long queryCount(StringBuilder querySql, T o, Class<Long> longClass) {
    return this.namedParameterJdbcTemplate.queryForObject(querySql.toString(), new BeanPropertySqlParameterSource(o), longClass);
  }

  protected Long queryCount(StringBuilder querySql, Class<Long> longClass) {
    return this.namedParameterJdbcTemplate.queryForObject(querySql.toString(), new MapSqlParameterSource(), longClass);
  }

  protected void init(String tableName, Set<String> primaryKey, Set<String> columns, Map<String, String> columnsParameter, Set<String> virtualColumns, Map<String, String> sort) {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + tableName);
      log.debug(ToolUtil.LOG + primaryKey);
      log.debug(ToolUtil.LOG + columns);
      log.debug(ToolUtil.LOG + columnsParameter);
      log.debug(ToolUtil.LOG + virtualColumns);
      log.debug(ToolUtil.LOG + sort);
    }
    if (ToolUtil.isNotNullStr(tableName)) {
      MapSqlParameterSource dtMapSqlParameterSource = new MapSqlParameterSource();
      dtMapSqlParameterSource.addValue("dtName", tableName);
      Collection<Dt> dtSet = this.namedParameterJdbcTemplate.query("SELECT dt.DT_ID, dt.DT_SQL, dt.DT_NAME, dt.DT_NAME_ANNOTATION, dt.DT_PREFIX, dt.INITIAL_CASE_ENTITY_NAME, dt.INITIAL_LOWERCASE_ENTITY_NAME, dt.PRO_PATH, dt.PRO_ALL_NAME FROM PRO_DT dt WHERE dt.DT_ID IS NOT NULL AND dt.DT_NAME = :dtName", dtMapSqlParameterSource, BeanPropertyRowMapper.newInstance(Dt.class));
      if (ToolUtil.isNotEmpty(dtSet)) {
        Dt dt = dtSet.iterator().next();
        MapSqlParameterSource pkMapSqlParameterSource = new MapSqlParameterSource();
        pkMapSqlParameterSource.addValue("dtId", dt.getDtId());
        Collection<Pk> pkSet = this.namedParameterJdbcTemplate.query("SELECT pk.PK_ID, pk.DT_ID, pk.COLUMNS_ID FROM PRO_PK pk WHERE pk.PK_ID IS NOT NULL AND pk.DT_ID = :dtId", pkMapSqlParameterSource, BeanPropertyRowMapper.newInstance(Pk.class));
        if (ToolUtil.isNotEmpty(pkSet)) {
          Set<String> inColumnsId = new LinkedHashSet<>();
          for (Pk pk : pkSet) {
            inColumnsId.add(pk.getColumnsId());
          }
          MapSqlParameterSource columnsMapSqlParameterSource = new MapSqlParameterSource();
          columnsMapSqlParameterSource.addValue("inColumnsId", new ArrayList<>(inColumnsId));
          Collection<Columns> columnsSet = this.namedParameterJdbcTemplate.query("SELECT columns.COLUMNS_ID, columns.COLUMN_NAME, columns.COLUMN_NAME_ANNOTATION, columns.DATA_TYPE, columns.IS_NULL, columns.INITIAL_CASE_COLUMN_NAME, columns.INITIAL_LOWERCASE_COLUMN_NAME, columns.WEIGHT_ORDER, columns.DT_ID FROM PRO_COLUMNS columns WHERE columns.COLUMNS_ID IS NOT NULL AND columns.COLUMNS_ID IN ( :inColumnsId )", columnsMapSqlParameterSource, BeanPropertyRowMapper.newInstance(Columns.class));
          if (ToolUtil.isNotEmpty(columnsSet)) {
            for (Columns pk : columnsSet) {
              primaryKey.add(pk.getInitialLowercaseColumnName());
            }
          }
        }
        MapSqlParameterSource columnsMapSqlParameterSource = new MapSqlParameterSource();
        columnsMapSqlParameterSource.addValue("dtId", dt.getDtId());
        Collection<Columns> columnsSet = this.namedParameterJdbcTemplate.query("SELECT columns.COLUMNS_ID, columns.COLUMN_NAME, columns.COLUMN_NAME_ANNOTATION, columns.DATA_TYPE, columns.IS_NULL, columns.INITIAL_CASE_COLUMN_NAME, columns.INITIAL_LOWERCASE_COLUMN_NAME, columns.WEIGHT_ORDER, columns.DT_ID FROM PRO_COLUMNS columns WHERE columns.COLUMNS_ID IS NOT NULL AND columns.DT_ID = :dtId ORDER BY columns.WEIGHT_ORDER ASC", columnsMapSqlParameterSource, BeanPropertyRowMapper.newInstance(Columns.class));
        if (ToolUtil.isNotEmpty(columnsSet)) {
          for (Columns column : columnsSet) {
            columns.add(column.getInitialLowercaseColumnName());
            columnsParameter.put(column.getInitialLowercaseColumnName(), column.getColumnName());
          }
        }
        MapSqlParameterSource virtualColumnsMapSqlParameterSource = new MapSqlParameterSource();
        virtualColumnsMapSqlParameterSource.addValue("dtId", dt.getDtId());
        Collection<VirtualColumns> virtualColumnsSet = this.namedParameterJdbcTemplate.query("SELECT virtualColumns.VIRTUAL_COLUMNS_ID, virtualColumns.SOURCE_DT_ID, virtualColumns.TARGET_DT_ID, virtualColumns.SOURCE_COLUMNS_ID, virtualColumns.TARGET_COLUMNS_ID, virtualColumns.TARGET_DISPLAY_COLUMNS_ID, virtualColumns.DISPLAY_COLUMNS_ALIAS, virtualColumns.VIRTUAL_COLUMNS_SQL, virtualColumns.WEIGHT_ORDER FROM PRO_VIRTUAL_COLUMNS virtualColumns WHERE virtualColumns.VIRTUAL_COLUMNS_ID IS NOT NULL AND virtualColumns.SOURCE_DT_ID = :dtId ORDER BY virtualColumns.WEIGHT_ORDER ASC", virtualColumnsMapSqlParameterSource, BeanPropertyRowMapper.newInstance(VirtualColumns.class));
        if (ToolUtil.isNotEmpty(virtualColumnsSet)) {
          for (VirtualColumns virtualColumn : virtualColumnsSet) {
            virtualColumns.add(virtualColumn.getVirtualColumnsSql());
          }
        }
        MapSqlParameterSource sortMapSqlParameterSource = new MapSqlParameterSource();
        sortMapSqlParameterSource.addValue("dtId", dt.getDtId());
        Collection<Sort> sortSet = this.namedParameterJdbcTemplate.query("SELECT sort.SORT_ID, sort.SORT_RULE, sort.DT_ID, sort.COLUMNS_ID FROM PRO_SORT sort WHERE sort.SORT_ID IS NOT NULL AND sort.DT_ID = :dtId", sortMapSqlParameterSource, BeanPropertyRowMapper.newInstance(Sort.class));
        if (ToolUtil.isNotEmpty(sortSet)) {
          for (Sort eachSort : sortSet) {
            MapSqlParameterSource sortColumnsMapSqlParameterSource = new MapSqlParameterSource();
            sortColumnsMapSqlParameterSource.addValue("columnsId", eachSort.getColumnsId());
            sortColumnsMapSqlParameterSource.addValue("dtId", dt.getDtId());
            Collection<Columns> sortColumnsSet = this.namedParameterJdbcTemplate.query("SELECT columns.COLUMNS_ID, columns.COLUMN_NAME, columns.COLUMN_NAME_ANNOTATION, columns.DATA_TYPE, columns.IS_NULL, columns.INITIAL_CASE_COLUMN_NAME, columns.INITIAL_LOWERCASE_COLUMN_NAME, columns.WEIGHT_ORDER, columns.DT_ID FROM PRO_COLUMNS columns WHERE columns.COLUMNS_ID IS NOT NULL AND columns.COLUMNS_ID = :columnsId AND columns.DT_ID = :dtId", sortColumnsMapSqlParameterSource, BeanPropertyRowMapper.newInstance(Columns.class));
            if (ToolUtil.isNotEmpty(sortColumnsSet)) {
              sort.put(sortColumnsSet.iterator().next().getInitialLowercaseColumnName(), eachSort.getSortRule());
            }
          }
        }
      }
    }
  }

}
