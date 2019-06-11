package cn.moonnow.code.business.service.impl;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.moonnow.code.business.param.DtParam;
import cn.moonnow.code.business.service.ICodingService;
import cn.moonnow.code.business.vo.ConfigVO;
import cn.moonnow.code.entity.Columns;
import cn.moonnow.code.entity.Dt;
import cn.moonnow.code.entity.Pk;
import cn.moonnow.code.entity.Query;
import cn.moonnow.code.entity.Sort;
import cn.moonnow.code.entity.VirtualColumns;
import cn.moonnow.code.persistent.IColumnsPersistent;
import cn.moonnow.code.persistent.IDtPersistent;
import cn.moonnow.code.persistent.IPkPersistent;
import cn.moonnow.code.persistent.IQueryPersistent;
import cn.moonnow.code.persistent.ISortPersistent;
import cn.moonnow.code.persistent.IVirtualColumnsPersistent;
import cn.moonnow.code.query.ColumnsQuery;
import cn.moonnow.code.query.DtQuery;
import cn.moonnow.code.query.PkQuery;
import cn.moonnow.code.query.QueryQuery;
import cn.moonnow.code.query.SortQuery;
import cn.moonnow.code.query.VirtualColumnsQuery;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 生成代码 服务实现
 */
@Log4j2
@Service("cn.moonnow.code.CodingService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = Exception.class)
public class CodingServiceImpl implements ICodingService {

  public static final String LOG = "CodingService";

  @Resource(name = "cn.moonnow.code.DtPersistent")
  private IDtPersistent dtPersistent;

  @Resource(name = "cn.moonnow.code.ColumnsPersistent")
  private IColumnsPersistent columnsPersistent;

  @Resource(name = "cn.moonnow.code.PkPersistent")
  private IPkPersistent pkPersistent;

  @Resource(name = "cn.moonnow.code.QueryPersistent")
  private IQueryPersistent queryPersistent;

  @Resource(name = "cn.moonnow.code.SortPersistent")
  private ISortPersistent sortPersistent;

  @Resource(name = "cn.moonnow.code.VirtualColumnsPersistent")
  private IVirtualColumnsPersistent virtualColumnsPersistent;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Dt saveDtInfo(DtParam dtParam) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dtParam);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dtParam)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Dt dt = new Dt();
      dt.setDtId(ToolUtil.getUUID());
      dt.setDtSql(ToolUtil.getTableInfoStrFromSqlPath(dtParam.getSqlFilePath()));
      dt.setDtName(ToolUtil.getDtNameFromDtStr(dt.getDtSql()));
      dt.setDtPrefix(dtParam.getDtPrefix());
      dt.setProPath(dtParam.getProPath());
      dt.setProAllName(dtParam.getProAllName());
      DtQuery dtQuery = new DtQuery();
      dtQuery.setDtNameAndeq(dt.getDtName());
      if (dtPersistent.getCountDt(dtQuery) > 0) {
        throw new ToolException("表名：" + dt.getDtName() + " 不能重复");
      }
      return dtPersistent.saveDt(dt);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void initData() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      Collection<Dt> dtSet = dtPersistent.getAllDt();
      if (ToolUtil.isNotEmpty(dtSet)) {
        for (Dt dt : dtSet) {
          this.extract(dt);
        }
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void extract(Dt dt) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dt);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(dt.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      // 取到表数据并且设值然后更新数据
      dt = dtPersistent.getDtByPk(dt.getDtId());
      dt.setDtNameAnnotation(ToolUtil.getDtNameAnnotationFromDtStr(dt.getDtSql()));
      if (ToolUtil.isNullStr(dt.getDtPrefix())) {
        dt.setInitialCaseEntityName(ToolUtil.getInitialCaseOrInitialLowercaseHumpFromStr(dt.getDtName(), true));
        dt.setInitialLowercaseEntityName(ToolUtil.getInitialCaseOrInitialLowercaseHumpFromStr(dt.getDtName(), false));
      } else {
        dt.setInitialCaseEntityName(ToolUtil.getInitialCaseOrInitialLowercaseHumpFromStr(dt.getDtName().replaceAll(dt.getDtPrefix(), "").trim(), true));
        dt.setInitialLowercaseEntityName(ToolUtil.getInitialCaseOrInitialLowercaseHumpFromStr(dt.getDtName().replaceAll(dt.getDtPrefix(), "").trim(), false));
      }
      dtPersistent.updateDt(dt);
      // 删除表相关的虚拟列数据
      VirtualColumnsQuery virtualColumnsSourceQuery = new VirtualColumnsQuery();
      virtualColumnsSourceQuery.setSourceDtId(dt.getDtId());
      Collection<VirtualColumns> delVirtualColumnsSource = virtualColumnsPersistent.queryVirtualColumns(virtualColumnsSourceQuery);
      if (ToolUtil.isNotEmpty(delVirtualColumnsSource)) {
        virtualColumnsPersistent.batchRemoveVirtualColumns(delVirtualColumnsSource);
      }
      VirtualColumnsQuery virtualColumnsTargetQuery = new VirtualColumnsQuery();
      virtualColumnsTargetQuery.setTargetDtId(dt.getDtId());
      Collection<VirtualColumns> delVirtualColumnsTarget = virtualColumnsPersistent.queryVirtualColumns(virtualColumnsTargetQuery);
      if (ToolUtil.isNotEmpty(delVirtualColumnsTarget)) {
        virtualColumnsPersistent.batchRemoveVirtualColumns(delVirtualColumnsTarget);
      }
      // 删除表相关的查询数据
      QueryQuery queryQuery = new QueryQuery();
      queryQuery.setDtId(dt.getDtId());
      Collection<Query> delQuery = queryPersistent.queryQuery(queryQuery);
      if (ToolUtil.isNotEmpty(delQuery)) {
        queryPersistent.batchRemoveQuery(delQuery);
      }
      // 删除表相关的排序数据
      SortQuery sortQuery = new SortQuery();
      sortQuery.setDtId(dt.getDtId());
      Collection<Sort> delSort = sortPersistent.querySort(sortQuery);
      if (ToolUtil.isNotEmpty(delSort)) {
        sortPersistent.batchRemoveSort(delSort);
      }
      // 删除表的主键数据
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> delPk = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(delPk)) {
        pkPersistent.batchRemovePk(delPk);
      }
      // 删除表的列数据
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setDtId(dt.getDtId());
      Collection<Columns> delColumns = columnsPersistent.queryColumns(columnsQuery);
      if (ToolUtil.isNotEmpty(delColumns)) {
        columnsPersistent.batchRemoveColumns(delColumns);
      }
      // 取表的列map
      LinkedHashMap<Integer, String> columnsMap = ToolUtil.getColumnsMapFromDtStr(dt.getDtSql());
      String primaryKey = "";
      boolean isOr = true;
      LinkedHashMap<String, String> queryMap = new LinkedHashMap<>();
      LinkedHashMap<String, String> keyLikeMap = new LinkedHashMap<>();
      if (ToolUtil.isNotEmpty(columnsMap)) {
        LinkedHashSet<Columns> saveColumns = new LinkedHashSet<Columns>();
        for (Entry<Integer, String> entry : columnsMap.entrySet()) {
          if (entry.getValue().indexOf("`") == 0) {
            // 判断是否为列数据 如果是 就保存列数据
            Columns columns = new Columns();
            columns.setColumnsId(ToolUtil.getUUID());
            columns.setColumnName(ToolUtil.getColumnsNameFromDtColumnsStr(entry.getValue()));
            columns.setColumnNameAnnotation(ToolUtil.getColumnsNameAnnotationFromDtColumnsStr(entry.getValue()));
            columns.setDataType(ToolUtil.getDataTypeFromDtColumnsStr(entry.getValue()));
            columns.setIsNull(ToolUtil.getIsNullFromDtColumnsStr(entry.getValue()));
            columns.setInitialCaseColumnName(ToolUtil.getInitialCaseOrInitialLowercaseHumpFromStr(columns.getColumnName(), true));
            columns.setInitialLowercaseColumnName(ToolUtil.getInitialCaseOrInitialLowercaseHumpFromStr(columns.getColumnName(), false));
            columns.setWeightOrder(entry.getKey());
            columns.setDtId(dt.getDtId());
            saveColumns.add(columns);
          } else if (entry.getValue().indexOf("PRIMARY KEY") == 0) {
            // 判断是否为主键 如果是 就设主键的值
            primaryKey = ToolUtil.getPrimaryKeyColumnNameFromDtColumnsStr(entry.getValue());
          } else if (entry.getValue().indexOf("UNIQUE KEY") == 0) {
            // 唯一列的判断
            if (isOr) {
              if (-1 != entry.getValue().indexOf("(`")) {
                String str = entry.getValue().substring(entry.getValue().indexOf("(`") + 2);
                queryMap.put(str.substring(0, str.indexOf("`")), "Andeq");
                str = str.substring(str.indexOf("`") + 1);
                for (int i = 1; i > 0; i++) {
                  if (-1 != str.indexOf("`")) {
                    str = str.substring(str.indexOf("`") + 1);
                    queryMap.put(str.substring(0, str.indexOf("`")), "Andeq");
                    str = str.substring(str.indexOf("`") + 1);
                  } else {
                    break;
                  }
                }
                isOr = false;
              }
            } else {
              if (-1 != entry.getValue().indexOf("(`")) {
                String str = entry.getValue().substring(entry.getValue().indexOf("(`") + 2);
                queryMap.put(str.substring(0, str.indexOf("`")), "Oreq");
                str = str.substring(str.indexOf("`") + 1);
                for (int i = 1; i > 0; i++) {
                  if (-1 != str.indexOf("`")) {
                    str = str.substring(str.indexOf("`") + 1);
                    queryMap.put(str.substring(0, str.indexOf("`")), "Andeq");
                    str = str.substring(str.indexOf("`") + 1);
                  } else {
                    break;
                  }
                }
              }
            }
          }
        }
        columnsPersistent.batchSaveColumns(saveColumns);
        // 添加关键字查询的查询数据
        boolean isFirst = true;
        if (ToolUtil.isNotEmpty(saveColumns)) {
          if (ToolUtil.isNotNullStr(primaryKey)) {
            for (Columns column : saveColumns) {
              if (!primaryKey.equals(column.getColumnName())) {
                if ("String".equals(ToolUtil.getJavaDataTypeFromDtDataTypeStr(column.getDataType()))) {
                  if (isFirst) {
                    isFirst = false;
                    keyLikeMap.put(column.getColumnName(), "AndKeyLike");
                  } else {
                    keyLikeMap.put(column.getColumnName(), "OrKeyLike");
                  }
                }
              }
            }
          }
        }
      }
      // 主键不为空就保存主键数据
      if (ToolUtil.isNotNullStr(primaryKey)) {
        ColumnsQuery primaryKeyColumnsQuery = new ColumnsQuery();
        primaryKeyColumnsQuery.setColumnName(primaryKey);
        primaryKeyColumnsQuery.setDtId(dt.getDtId());
        Collection<Columns> primaryKeyColumns = columnsPersistent.queryColumns(primaryKeyColumnsQuery);
        if (ToolUtil.isNotEmpty(primaryKeyColumns)) {
          Pk pk = new Pk();
          pk.setPkId(ToolUtil.getUUID());
          pk.setDtId(dt.getDtId());
          pk.setColumnsId(primaryKeyColumns.iterator().next().getColumnsId());
          pkPersistent.savePk(pk);
        }
      }
      // 查询不为空时 保存主键in查询数据和唯一列查询数据
      if (ToolUtil.isNotEmpty(queryMap) || ToolUtil.isNotNullStr(primaryKey) || ToolUtil.isNotEmpty(keyLikeMap)) {
        LinkedHashSet<Query> saveQuery = new LinkedHashSet<Query>();
        int weightOrder = 1;
        if (ToolUtil.isNotNullStr(primaryKey)) {
          ColumnsQuery primaryKeyColumnsQuery = new ColumnsQuery();
          primaryKeyColumnsQuery.setColumnName(primaryKey);
          primaryKeyColumnsQuery.setDtId(dt.getDtId());
          Collection<Columns> primaryKeyColumns = columnsPersistent.queryColumns(primaryKeyColumnsQuery);
          if (ToolUtil.isNotEmpty(primaryKeyColumns)) {
            Query query = new Query();
            query.setQueryId(ToolUtil.getUUID());
            query.setQueryType("Andin");
            query.setDtId(dt.getDtId());
            query.setColumnsId(primaryKeyColumns.iterator().next().getColumnsId());
            query.setWeightOrder(weightOrder);
            saveQuery.add(query);
            weightOrder++;
          }
        }
        if (ToolUtil.isNotEmpty(queryMap)) {
          for (Entry<String, String> entry : queryMap.entrySet()) {
            ColumnsQuery queryColumnsQuery = new ColumnsQuery();
            queryColumnsQuery.setColumnName(entry.getKey());
            queryColumnsQuery.setDtId(dt.getDtId());
            Collection<Columns> queryColumns = columnsPersistent.queryColumns(queryColumnsQuery);
            if (ToolUtil.isNotEmpty(queryColumns)) {
              Query query = new Query();
              query.setQueryId(ToolUtil.getUUID());
              query.setQueryType(entry.getValue());
              query.setDtId(dt.getDtId());
              query.setColumnsId(queryColumns.iterator().next().getColumnsId());
              query.setWeightOrder(weightOrder);
              saveQuery.add(query);
              weightOrder++;
            }
          }
        }
        if (ToolUtil.isNotEmpty(keyLikeMap)) {
          for (Entry<String, String> entry : keyLikeMap.entrySet()) {
            ColumnsQuery queryColumnsQuery = new ColumnsQuery();
            queryColumnsQuery.setColumnName(entry.getKey());
            queryColumnsQuery.setDtId(dt.getDtId());
            Collection<Columns> queryColumns = columnsPersistent.queryColumns(queryColumnsQuery);
            if (ToolUtil.isNotEmpty(queryColumns)) {
              Query query = new Query();
              query.setQueryId(ToolUtil.getUUID());
              query.setQueryType(entry.getValue());
              query.setDtId(dt.getDtId());
              query.setColumnsId(queryColumns.iterator().next().getColumnsId());
              query.setWeightOrder(weightOrder);
              saveQuery.add(query);
              weightOrder++;
            }
          }
        }
        queryPersistent.batchSaveQuery(saveQuery);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public ConfigVO getConfig(Dt dt) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dt);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(dt.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      ConfigVO configVo = new ConfigVO();
      dt = dtPersistent.getDtByPk(dt.getDtId());
      configVo.setProjectPath(dt.getProPath());
      configVo.setJdbcEntityFilePath(ToolUtil.getJdbcEntityFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialCaseEntityName()));
      configVo.setPersistentInterfaceFilePath(ToolUtil.getIPersistentFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialCaseEntityName()));
      configVo.setJdbcPersistentImplFilePath(ToolUtil.getJdbcPersistentImplFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialCaseEntityName()));
      configVo.setServiceInterfaceFilePath(ToolUtil.getIServiceFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialCaseEntityName()));
      configVo.setServiceImplFilePath(ToolUtil.getServiceImplFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialCaseEntityName()));
      configVo.setControllerFilePath(ToolUtil.getControllerFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialCaseEntityName()));
      configVo.setQueryFilePath(ToolUtil.getQueryFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialCaseEntityName()));
      configVo.setVoFilePath(ToolUtil.getVoFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialCaseEntityName()));
      return configVo;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void codingJdbcEntity(ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + configVo);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder entityStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(configVo.getDtId());
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setDtId(dt.getDtId());
      Collection<Columns> columnsSet = columnsPersistent.queryColumns(columnsQuery);
      configMap.putAll(ToolUtil.CONFIG_MAP);
      configMap.put("entityPackageName", ToolUtil.getEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("dtName", dt.getDtName());
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      if (ToolUtil.getIsImportBigDecimalFromDtSqlStr(dt.getDtSql())) {
        entityStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/entity/jdbc/1.txt"), configMap));
      } else {
        entityStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/entity/jdbc/2.txt"), configMap));
      }
      if (ToolUtil.isNotEmpty(columnsSet)) {
        for (Columns eachColumns : columnsSet) {
          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
          columnConfigMap.putAll(configMap);
          columnConfigMap.put("dataType", ToolUtil.getJavaDataTypeFromDtDataTypeStr(eachColumns.getDataType()));
          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
          columnConfigMap.put("columnNameAnnotation", eachColumns.getColumnNameAnnotation());
          entityStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/entity/jdbc/3.txt"), columnConfigMap)).append("\n");
        }
      }
      entityStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/entity/jdbc/4.txt"), configMap));
      ToolUtil.getFileFromContentStrAndPath(entityStr.toString(), configVo.getJdbcEntityFilePath());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void codingQuery(ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + configVo);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder queryStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(configVo.getDtId());
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setDtId(dt.getDtId());
      Collection<Columns> columnsSet = columnsPersistent.queryColumns(columnsQuery);
      QueryQuery queryQuery = new QueryQuery();
      queryQuery.setDtId(dt.getDtId());
      Collection<Query> querySet = queryPersistent.queryQuery(queryQuery);
      configMap.putAll(ToolUtil.CONFIG_MAP);
      configMap.put("queryPackageName", ToolUtil.getQueryPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("dtName", dt.getDtName());
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      if (ToolUtil.getIsImportBigDecimalFromDtSqlStr(dt.getDtSql())) {
        queryStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/query/1.txt"), configMap)).append("\n");
      } else {
        queryStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/query/2.txt"), configMap)).append("\n");
      }
      if (ToolUtil.isNotEmpty(columnsSet)) {
        for (Columns eachColumns : columnsSet) {
          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
          columnConfigMap.putAll(configMap);
          columnConfigMap.put("dataType", ToolUtil.getJavaDataTypeFromDtDataTypeStr(eachColumns.getDataType()));
          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
          columnConfigMap.put("columnNameAnnotation", eachColumns.getColumnNameAnnotation());
          queryStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/query/3.txt"), columnConfigMap)).append("\n");
        }
      }
      if (ToolUtil.isNotEmpty(querySet)) {
        for (Query eachQuery : querySet) {
          if ("Andin".equals(eachQuery.getQueryType()) || "Andnin".equals(eachQuery.getQueryType()) || "Orin".equals(eachQuery.getQueryType()) || "Ornin".equals(eachQuery.getQueryType())) {
            LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
            columnConfigMap.putAll(configMap);
            Columns columns = columnsPersistent.getColumnsByPk(eachQuery.getColumnsId());
            columnConfigMap.put("dataType", "List<" + ToolUtil.getJavaDataTypeFromDtDataTypeStr(columns.getDataType()) + ">");
            columnConfigMap.put("initialLowercaseColumnName", columns.getInitialLowercaseColumnName() + eachQuery.getQueryType());
            columnConfigMap.put("columnNameAnnotation", columns.getColumnNameAnnotation() + eachQuery.getQueryType() + "查询");
            queryStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/query/3.txt"), columnConfigMap)).append("\n");
          } else {
            LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
            columnConfigMap.putAll(configMap);
            Columns columns = columnsPersistent.getColumnsByPk(eachQuery.getColumnsId());
            columnConfigMap.put("dataType", ToolUtil.getJavaDataTypeFromDtDataTypeStr(columns.getDataType()));
            columnConfigMap.put("initialLowercaseColumnName", columns.getInitialLowercaseColumnName() + eachQuery.getQueryType());
            columnConfigMap.put("columnNameAnnotation", columns.getColumnNameAnnotation() + eachQuery.getQueryType() + "查询");
            queryStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/query/3.txt"), columnConfigMap)).append("\n");
          }
        }
      }
      queryStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/query/4.txt"), configMap));
      ToolUtil.getFileFromContentStrAndPath(queryStr.toString(), configVo.getQueryFilePath());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void codingVo(ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + configVo);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder voStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(configVo.getDtId());
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setDtId(dt.getDtId());
      Collection<Columns> columnsSet = columnsPersistent.queryColumns(columnsQuery);
      configMap.putAll(ToolUtil.CONFIG_MAP);
      configMap.put("VOPackageName", ToolUtil.getVOPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("dtName", dt.getDtName());
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      if (ToolUtil.getIsImportBigDecimalFromDtSqlStr(dt.getDtSql())) {
        voStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/vo/1.txt"), configMap)).append("\n");
      } else {
        voStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/vo/2.txt"), configMap)).append("\n");
      }
      if (ToolUtil.isNotEmpty(columnsSet)) {
        for (Columns eachColumns : columnsSet) {
          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
          columnConfigMap.putAll(configMap);
          columnConfigMap.put("dataType", ToolUtil.getJavaDataTypeFromDtDataTypeStr(eachColumns.getDataType()));
          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
          columnConfigMap.put("columnNameAnnotation", eachColumns.getColumnNameAnnotation());
          voStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/vo/3.txt"), columnConfigMap)).append("\n");
        }
      }
      voStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/vo/4.txt"), configMap));
      ToolUtil.getFileFromContentStrAndPath(voStr.toString(), configVo.getVoFilePath());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void codingIPersistent(ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + configVo);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder iPersistentStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(configVo.getDtId());
      configMap.putAll(ToolUtil.CONFIG_MAP);
      configMap.put("iPersistentPackageName", ToolUtil.getIPersistentPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("entityPackageName", ToolUtil.getEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("queryPackageName", ToolUtil.getQueryPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("VOPackageName", ToolUtil.getVOPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("dtName", dt.getDtName());
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          configMap.put("primaryKeyDataType", ToolUtil.getJavaDataTypeFromDtDataTypeStr(columns.getDataType()));
          configMap.put("primaryKeyInitialLowercaseColumnName", columns.getInitialLowercaseColumnName());
        }
      }
      iPersistentStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/i/persistent/1.txt"), configMap));
      ToolUtil.getFileFromContentStrAndPath(iPersistentStr.toString(), configVo.getPersistentInterfaceFilePath());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void codingJdbcPersistentImpl(ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + configVo);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder jdbcPersistentImplStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(configVo.getDtId());
      configMap.putAll(ToolUtil.CONFIG_MAP);
      configMap.put("persistentImplPackageName", ToolUtil.getJdbcPersistentImplPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("persistentImplNamePrefix", ToolUtil.getPersistentImplNamePrefixStrFromConfig(dt.getProAllName()));
      configMap.put("iPersistentPackageName", ToolUtil.getIPersistentPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("entityPackageName", ToolUtil.getEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("queryPackageName", ToolUtil.getQueryPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("VOPackageName", ToolUtil.getVOPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          configMap.put("primaryKeyDataType", ToolUtil.getJavaDataTypeFromDtDataTypeStr(columns.getDataType()));
          configMap.put("primaryKeyInitialCaseColumnName", columns.getInitialCaseColumnName());
          configMap.put("primaryKeyInitialLowercaseColumnName", columns.getInitialLowercaseColumnName());
        }
      }
      jdbcPersistentImplStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/impl/persistent/jdbc/1.txt"), configMap));
      ToolUtil.getFileFromContentStrAndPath(jdbcPersistentImplStr.toString(), configVo.getJdbcPersistentImplFilePath());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void codingIService(ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + configVo);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder iServiceStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(configVo.getDtId());
      configMap.putAll(ToolUtil.CONFIG_MAP);
      configMap.put("iServicePackageName", ToolUtil.getIServicePackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("entityPackageName", ToolUtil.getEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("queryPackageName", ToolUtil.getQueryPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("VOPackageName", ToolUtil.getVOPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          configMap.put("primaryKeyDataType", ToolUtil.getJavaDataTypeFromDtDataTypeStr(columns.getDataType()));
          configMap.put("primaryKeyInitialLowercaseColumnName", columns.getInitialLowercaseColumnName());
        }
      }
      iServiceStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/i/service/1.txt"), configMap));
      ToolUtil.getFileFromContentStrAndPath(iServiceStr.toString(), configVo.getServiceInterfaceFilePath());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void codingServiceImpl(ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + configVo);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder serviceImplStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(configVo.getDtId());
      QueryQuery queryQuery = new QueryQuery();
      queryQuery.setDtId(dt.getDtId());
      configMap.putAll(ToolUtil.CONFIG_MAP);
      configMap.put("serviceImplPackageName", ToolUtil.getServiceImplPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("serviceImplNamePrefix", ToolUtil.getServiceImplNamePrefixStrFromConfig(dt.getProAllName()));
      configMap.put("persistentImplNamePrefix", ToolUtil.getPersistentImplNamePrefixStrFromConfig(dt.getProAllName()));
      configMap.put("iServicePackageName", ToolUtil.getIServicePackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("iPersistentPackageName", ToolUtil.getIPersistentPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("entityPackageName", ToolUtil.getEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("queryPackageName", ToolUtil.getQueryPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("VOPackageName", ToolUtil.getVOPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          configMap.put("primaryKeyDataType", ToolUtil.getJavaDataTypeFromDtDataTypeStr(columns.getDataType()));
          configMap.put("primaryKeyInitialCaseColumnName", columns.getInitialCaseColumnName());
          configMap.put("primaryKeyInitialLowercaseColumnName", columns.getInitialLowercaseColumnName());
        }
      }
      serviceImplStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/impl/service/1.txt"), configMap));
      ToolUtil.getFileFromContentStrAndPath(serviceImplStr.toString(), configVo.getServiceImplFilePath());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void codingController(ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + configVo);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder controllerStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(configVo.getDtId());
      configMap.putAll(ToolUtil.CONFIG_MAP);
      configMap.put("controllerPackageName", ToolUtil.getControllerPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("controllerUrl", ToolUtil.getControllerUrlStrFromConfig(dt.getProAllName()));
      configMap.put("iServicePackageName", ToolUtil.getIServicePackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("serviceImplNamePrefix", ToolUtil.getServiceImplNamePrefixStrFromConfig(dt.getProAllName()));
      configMap.put("entityPackageName", ToolUtil.getEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("queryPackageName", ToolUtil.getQueryPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("VOPackageName", ToolUtil.getVOPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          configMap.put("primaryKeyInitialCaseColumnName", columns.getInitialCaseColumnName());
        }
      }
      controllerStr.append(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/controller/1.txt"), configMap));
      ToolUtil.getFileFromContentStrAndPath(controllerStr.toString(), configVo.getControllerFilePath());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void codingProject(ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + configVo);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Dt dt = dtPersistent.getDtByPk(configVo.getDtId());
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      configMap.putAll(ToolUtil.CONFIG_MAP);
      configMap.put("projectName", dt.getProAllName());
      configMap.put("projectVersion", "9.9.6.ICU");
      String name = "";
      String proAllName = dt.getProAllName();
      String proPath = "";
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(configVo.getProjectPath().substring(configVo.getProjectPath().length() - 1, configVo.getProjectPath().length()))) {
        proPath = configVo.getProjectPath() + "/";
      } else {
        proPath = configVo.getProjectPath();
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "sql/README.md");
      ToolUtil.getFileFromContentStrAndPath(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/project/1.txt"), configMap), proPath + "pom.xml");
      ToolUtil.getFileFromContentStrAndPath(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/git/1.txt"), new LinkedHashMap<String, String>()), proPath + ".gitignore");
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      ToolUtil.getFileFromContentStrAndPath(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/project/2.txt"), configMap), proPath + "entity/pom.xml");
      ToolUtil.getFileFromContentStrAndPath(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/git/1.txt"), new LinkedHashMap<String, String>()), proPath + "entity/.gitignore");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "entity/src/main/java/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "entity/src/main/resources/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "entity/src/test/java/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "entity/src/test/resources/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "entity/src/main/java/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "entity/README.md");
      ToolUtil.getFileFromContentStrAndPath(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/project/3.txt"), configMap), proPath + "interface/pom.xml");
      ToolUtil.getFileFromContentStrAndPath(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/git/1.txt"), new LinkedHashMap<String, String>()), proPath + "interface/.gitignore");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "interface/src/main/java/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "interface/src/main/resources/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "interface/src/test/java/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "interface/src/test/resources/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "interface/src/main/java/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "persistent/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "interface/src/main/java/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "service/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "interface/src/main/java/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "query/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "interface/src/main/java/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "vo/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "interface/src/main/java/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "business/persistent/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "interface/src/main/java/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "business/service/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "interface/src/main/java/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "business/vo/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "interface/src/main/java/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "mapper/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "interface/src/main/resources/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "mapping/README.md");
      ToolUtil.getFileFromContentStrAndPath(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/project/4.txt"), configMap), proPath + "persistent/pom.xml");
      ToolUtil.getFileFromContentStrAndPath(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/git/1.txt"), new LinkedHashMap<String, String>()), proPath + "persistent/.gitignore");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "persistent/src/main/java/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "persistent/src/main/resources/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "persistent/src/test/java/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "persistent/src/test/resources/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "persistent/src/main/java/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "persistent/impl/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "persistent/src/main/java/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "business/persistent/impl/README.md");
      ToolUtil.getFileFromContentStrAndPath(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/project/5.txt"), configMap), proPath + "service/pom.xml");
      ToolUtil.getFileFromContentStrAndPath(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/git/1.txt"), new LinkedHashMap<String, String>()), proPath + "service/.gitignore");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "service/src/main/java/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "service/src/main/resources/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "service/src/test/java/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "service/src/test/resources/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "service/src/main/java/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "service/impl/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "service/src/main/java/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "business/service/impl/README.md");
      ToolUtil.getFileFromContentStrAndPath(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/project/6.txt"), configMap), proPath + "rest/pom.xml");
      ToolUtil.getFileFromContentStrAndPath(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/git/1.txt"), new LinkedHashMap<String, String>()), proPath + "rest/.gitignore");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "rest/src/main/java/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "rest/src/main/resources/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "rest/src/test/java/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "rest/src/test/resources/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "rest/src/main/java/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "rest/README.md");
      ToolUtil.getFileFromContentStrAndPath(name, proPath + "rest/src/main/java/cn/moonnow/" + ToolUtil.getBusinessPathStrFromConfig(dt.getProAllName()) + "business/rest/README.md");
      ToolUtil.getFileFromContentStrAndPath(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/project/7.txt"), configMap), proPath + "rely/pom.xml");
      ToolUtil.getFileFromContentStrAndPath(ToolUtil.renderString(ToolUtil.getStrFromFileResourcesPath("/java/git/1.txt"), new LinkedHashMap<String, String>()), proPath + "rely/.gitignore");
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

}
