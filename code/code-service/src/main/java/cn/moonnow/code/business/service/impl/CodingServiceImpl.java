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

}
