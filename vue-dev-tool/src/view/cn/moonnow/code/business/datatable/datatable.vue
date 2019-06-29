<style lang="less" scoped>
  @import './datatable.less';
</style>

<template>
  <div class="demo-split">
    <Split v-model="split">
      <div slot="left">
        <Card>
          <Row>
            <Col :xs="16" :sm="14" :md="10" :lg="10">
              <Input placeholder="关键字搜索" search enter-button :disabled="dtKeySearchSubmitting" @on-search="keySearchDt($event)" />
            </Col>
            <Col :xs="8" :sm="10" :md="14" :lg="14">
              <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="500" v-model="dtQueryPoptipShow">
                <Button icon="ios-locate-outline">精准查询</Button>
                <div slot="content">
                  <Form class="pro-margin-top-10" ref="dtQueryForm" :model="dtQuery" :label-width="120">
                    <Row>
                      <Col span="12">
                        <FormItem label="表名：" prop="dtName">
                          <Input v-model="dtQuery.dtName" placeholder="输入表名"></Input>
                        </FormItem>
                      </Col>
                      <Col span="12">
                        <FormItem label="表名注释：" prop="dtNameAnnotation">
                          <Input v-model="dtQuery.dtNameAnnotation" placeholder="输入表名注释"></Input>
                        </FormItem>
                      </Col>
                      <Col span="12">
                        <FormItem label="首字母大写实体类名：" prop="initialCaseEntityName">
                          <Input v-model="dtQuery.initialCaseEntityName" placeholder="输入首字母大写实体类名"></Input>
                        </FormItem>
                      </Col>
                      <Col span="12">
                        <FormItem label="首字母小写实体类名：" prop="initialLowercaseEntityName">
                          <Input v-model="dtQuery.initialLowercaseEntityName" placeholder="输入首字母小写实体类名"></Input>
                        </FormItem>
                      </Col>
                      <Col span="12">
                        <FormItem label="数据库表前缀：" prop="dtPrefix">
                          <Input v-model="dtQuery.dtPrefix" placeholder="输入数据库表前缀"></Input>
                        </FormItem>
                      </Col>
                      <Col span="12">
                        <FormItem label="项目全称：" prop="proAllName">
                          <Input v-model="dtQuery.proAllName" placeholder="输入项目全称"></Input>
                        </FormItem>
                      </Col>
                      <Col span="24">
                        <FormItem label="项目路径：" prop="proPath">
                          <Input v-model="dtQuery.proPath" placeholder="输入项目路径"></Input>
                        </FormItem>
                      </Col>
                    </Row>
                    <Divider />
                    <Row>
                      <Col span="24">
                        <FormItem class="pro-float-right">
                          <Button type="primary" @click="vagueQueryDt()" :loading="dtQuerySubmitting">精准查询</Button>
                          <Button class="pro-margin-left-8" @click="resetDtQueryForm()">重置</Button>
                        </FormItem>
                      </Col>
                    </Row>
                  </Form>
                </div>
              </Poptip>
            </Col>
          </Row>
          <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addDt()">新建</Button>
          <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upDt()" v-if="dtSelectRowLength === 1">编辑</Button>
          <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delDt()" v-if="dtSelectRowLength > 0">
            <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
          </Poptip>
          <Button class="pro-list-button" icon="md-bulb" @click="detailDt()" v-if="dtSelectRowLength === 1">详情</Button>
          <Button class="pro-list-button" type="info" icon="ios-flash-outline" ghost @click="extractData()" v-if="dtSelectRowLength === 1">提取数据</Button>
          <Table :columns="dtColumns" :data="dtSet" :highlight-row="true" @on-selection-change="setDtSelectRow($event)" @on-row-dblclick="dblclickDetailDt($event)" @on-row-click="clickDtRow($event)"></Table>
          <Row class="pro-margin-top-10" type="flex" justify="center">
            <Page size="small" placement="top" show-total show-sizer show-elevator :total="dtCount" :current="dtPage" :page-size-opts="[10, 30, 50]" @on-change="dtPageIndexChange($event)" @on-page-size-change="dtPageSizeChange($event)" v-if="dtPageShow" />
            <Button type="text" shape="circle" icon="md-refresh" @click="dtRefresh()"></Button>
          </Row>
          <Spin size="large" fix v-if="dtSpinShow"></Spin>
        </Card>
      </div>
      <div slot="right">
        <Card>
          <Tabs v-model="currentTabName" @on-click="changeTabName($event)">
            <TabPane label="排序" icon="ios-funnel" name="sort">
              <Card>
                <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addSort()">新建</Button>
                <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upSort()" v-if="sortSelectRowLength === 1">编辑</Button>
                <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delSort()" v-if="sortSelectRowLength > 0">
                  <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
                </Poptip>
                <Button class="pro-list-button" icon="md-bulb" @click="detailSort()" v-if="sortSelectRowLength === 1">详情</Button>
                <Table :columns="sortColumns" :data="sortSet" @on-selection-change="setSortSelectRow($event)" @on-row-dblclick="dblclickDetailSort($event)"></Table>
                <Row class="pro-margin-top-10" type="flex" justify="center">
                  <Button type="text" shape="circle" icon="md-refresh" @click="sortRefresh()"></Button>
                </Row>
                <Spin size="large" fix v-if="sortSpinShow"></Spin>
              </Card>
            </TabPane>
            <TabPane label="虚拟列" icon="logo-vimeo" name="virtualColumns">
              <Card>
                <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addVirtualColumns()">新建</Button>
                <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delVirtualColumns()" v-if="virtualColumnsSelectRowLength > 0">
                  <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
                </Poptip>
                <Button class="pro-list-button" icon="md-bulb" @click="detailVirtualColumns()" v-if="virtualColumnsSelectRowLength === 1">详情</Button>
                <Table :columns="virtualColumnsColumns" :data="virtualColumnsSet" @on-selection-change="setVirtualColumnsSelectRow($event)" @on-row-dblclick="dblclickDetailVirtualColumns($event)"></Table>
                <Row class="pro-margin-top-10" type="flex" justify="center">
                  <Button type="text" shape="circle" icon="md-refresh" @click="virtualColumnsRefresh()"></Button>
                </Row>
                <Spin size="large" fix v-if="virtualColumnsSpinShow"></Spin>
              </Card>
            </TabPane>
          </Tabs>
        </Card>
      </div>
    </Split>
    <Modal width="70" v-model="dtEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑数据库表
        </p>
        <Form class="pro-margin-top-20" ref="dtForm" :model="dt" :rules="dtFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="8">
              <FormItem label="项目全称：" prop="proAllName">
                <Input v-model="dt.proAllName" placeholder="输入项目全称"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="8">
              <FormItem label="项目路径：" prop="proPath">
                <Input v-model="dt.proPath" placeholder="输入项目路径"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="8">
              <FormItem label="数据库表前缀：" prop="dtPrefix">
                <Input v-model="dt.dtPrefix" placeholder="输入数据库表前缀"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="24">
              <FormItem label="sql文件：">
                <Upload type="drag" action="//localhost/" :before-upload="handleUpload">
                  <div style="padding: 20px 0">
                    <Icon style="color: #3399ff" type="ios-cloud-upload" size="52"></Icon>
                    <p>选择sql文件</p>
                  </div>
                </Upload>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="24">
              <FormItem label="数据库表sql：" prop="dtSql">
                <Input v-model="dt.dtSql" type="textarea" :rows="16" placeholder="输入数据库表sql"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="11">
              <FormItem>
                <Button class="pro-margin-right-8" @click="toDtList()">取消</Button>
                <Button class="pro-margin-right-8" @click="resetDtForm()">重置</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="dtSubmitting" @click="saveDtForm()">保存</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="dtSubmitting" @click="saveAddDtForm()">继续录入</Button>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    </Modal>
    <Drawer width="45" v-model="dtDetailDrawerShow" :closable="false">
      <Card>
        <p slot="title">
          <Icon type="md-bulb"></Icon>
          数据库表详情
        </p>
        <Row>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据库表编号：{{ dt.dtId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="12">
            <p>表名：{{ dt.dtName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="12">
            <p>表名注释：{{ dt.dtNameAnnotation }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="12">
            <p>首字母大写实体类名：{{ dt.initialCaseEntityName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="12">
            <p>首字母小写实体类名：{{ dt.initialLowercaseEntityName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据库表前缀：{{ dt.dtPrefix }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>项目路径：{{ dt.proPath }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>项目全称：{{ dt.proAllName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据库表sql：{{ dt.dtSql }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
    <Modal width="60" v-model="sortEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑排序
        </p>
        <Form class="pro-margin-top-20" ref="sortForm" :model="sort" :rules="sortFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="数据库表：" prop="dtId">
                <Select v-model="sort.dtId" clearable filterable @on-change="changeDtOfSortEdit($event)">
                  <Option v-for="item in dtListOfSortEdit" :value="item.dtId" :key="item.dtId">{{ item.dtName + ' [ ' + item.dtNameAnnotation + ' ]' }}</Option>
                </Select>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="列：" prop="columnsId">
                <Select v-model="sort.columnsId" clearable filterable :disabled="columnsSelectDisabledOfSortEdit">
                  <Option v-for="item in columnsListOfSortEdit" :value="item.columnsId" :key="item.columnsId">{{ item.columnName + ' [ ' + item.columnNameAnnotation + ' ]' }}</Option>
                </Select>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="排序规则：" prop="sortRule">
                <RadioGroup v-model="sort.sortRule" type="button">
                  <Radio label="ASC"></Radio>
                  <Radio label="DESC"></Radio>
                </RadioGroup>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem>
                <Button class="pro-margin-right-8" @click="toSortList()">取消</Button>
                <Button class="pro-margin-right-8" @click="resetSortForm()">重置</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="sortSubmitting" @click="saveSortForm()">保存</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="sortSubmitting" @click="saveAddSortForm()">继续录入</Button>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    </Modal>
    <Drawer width="45" v-model="sortDetailDrawerShow" :closable="false">
      <Card>
        <p slot="title">
          <Icon type="md-bulb"></Icon>
          排序详情
        </p>
        <Row>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>排序编号：{{ sortVo.sortId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据库表编号：{{ sortVo.dtId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>列编号：{{ sortVo.columnsId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>列名注释：{{ sortVo.columnNameAnnotation }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>列名：{{ sortVo.columnName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>排序规则：{{ sortVo.sortRule }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
    <Modal width="60" v-model="virtualColumnsEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑虚拟列
        </p>
        <Form class="pro-margin-top-20" ref="virtualColumnsForm" :model="virtualColumns" :rules="virtualColumnsFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="源表：" prop="sourceDtId">
                <Select v-model="virtualColumns.sourceDtId" clearable filterable @on-change="changeSourceDt($event)">
                  <Option v-for="item in sourceDtList" :value="item.dtId" :key="item.dtId">{{ item.dtName + ' [ ' + item.dtNameAnnotation + ' ]' }}</Option>
                </Select>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="源表外键列：" prop="sourceColumnsId">
                <Select v-model="virtualColumns.sourceColumnsId" clearable filterable>
                  <Option v-for="item in sourceColumnsList" :value="item.columnsId" :key="item.columnsId">{{ item.columnName + ' [ ' + item.columnNameAnnotation + ' ]' }}</Option>
                </Select>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="目标表：" prop="targetDtId">
                <Select v-model="virtualColumns.targetDtId" clearable filterable @on-change="changeTargetDt($event)">
                  <Option v-for="item in targetDtList" :value="item.dtId" :key="item.dtId">{{ item.dtName + ' [ ' + item.dtNameAnnotation + ' ]' }}</Option>
                </Select>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="目标表关联列：" prop="targetColumnsId">
                <Select v-model="virtualColumns.targetColumnsId" clearable filterable>
                  <Option v-for="item in targetColumnsList" :value="item.columnsId" :key="item.columnsId">{{ item.columnName + ' [ ' + item.columnNameAnnotation + ' ]' }}</Option>
                </Select>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="目标表显示列：" prop="targetDisplayColumnsId">
                <Select v-model="virtualColumns.targetDisplayColumnsId" clearable filterable @on-change="changeDisplayColumns($event)">
                  <Option v-for="item in targetDisplayColumnsList" :value="item.columnsId" :key="item.columnsId">{{ item.columnName + ' [ ' + item.columnNameAnnotation + ' ]' }}</Option>
                </Select>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="显示列别名：" prop="displayColumnsAlias">
                <Input v-model="virtualColumns.displayColumnsAlias" placeholder="输入显示字段别名"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="排序权重：" prop="weightOrder">
                <InputNumber v-model="virtualColumns.weightOrder" :min="1"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem>
                <Button class="pro-margin-right-8" @click="toVirtualColumnsList()">取消</Button>
                <Button class="pro-margin-right-8" @click="resetVirtualColumnsForm()">重置</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="virtualColumnsSubmitting" @click="saveVirtualColumnsForm()">保存</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="virtualColumnsSubmitting" @click="saveAddVirtualColumnsForm()">继续录入</Button>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    </Modal>
    <Drawer width="45" v-model="virtualColumnsDetailDrawerShow" :closable="false">
      <Card>
        <p slot="title">
          <Icon type="md-bulb"></Icon>
          虚拟列详情
        </p>
        <Row>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>虚拟列编号：{{ virtualColumnsVo.virtualColumnsId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>源表数据库表编号：{{ virtualColumnsVo.sourceDtId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>源表列编号：{{ virtualColumnsVo.sourceColumnsId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>目标表数据库表编号：{{ virtualColumnsVo.targetDtId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>目标表列编号：{{ virtualColumnsVo.targetColumnsId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>目标表显示字段列编号：{{ virtualColumnsVo.targetDisplayColumnsId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="12">
            <p>关联列名：{{ virtualColumnsVo.columnName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="12">
            <p>关联表名：{{ virtualColumnsVo.dtName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="12">
            <p>显示字段别名：{{ virtualColumnsVo.displayColumnsAlias }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="12">
            <p>排序权重：{{ virtualColumnsVo.weightOrder }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>虚拟列sql：{{ virtualColumnsVo.virtualColumnsSql }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import Dt from '@/view/cn/moonnow/code/dt/Dt'
import DtQuery from '@/view/cn/moonnow/code/dt/DtQuery'
import Sort from '@/view/cn/moonnow/code/sort/Sort'
import SortQuery from '@/view/cn/moonnow/code/sort/SortQuery'
import SortVO from '@/view/cn/moonnow/code/sort/SortVO'
import VirtualColumns from '@/view/cn/moonnow/code/virtualColumns/VirtualColumns'
import VirtualColumnsQuery from '@/view/cn/moonnow/code/virtualColumns/VirtualColumnsQuery'
import VirtualColumnsVO from '@/view/cn/moonnow/code/virtualColumns/VirtualColumnsVO'
import Columns from '@/view/cn/moonnow/code/columns/Columns'
import ColumnsQuery from '@/view/cn/moonnow/code/columns/ColumnsQuery'

export default {
  data () {
    return {
      split: 0.5,
      currentTabName: 'sort',
      currentDtIdOfClickDt: null,
      dtColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '名称', key: 'dtNameAnnotation', minWidth: 130 },
        { title: '表名', key: 'dtName', minWidth: 220 },
        { title: '实体类名', key: 'initialCaseEntityName', minWidth: 150 },
        { title: '项目名称', key: 'proAllName', minWidth: 100 }
      ],
      dtFormValidate: {
        dtSql: [
          { required: true, message: '数据库表sql不能为空', trigger: 'blur' }
        ]
      },
      dtSet: [],
      dtCount: 0,
      dtRows: 10,
      dtPage: 1,
      dtSelectRow: [],
      dtSelectRowLength: 0,
      dt: new Dt(),
      dtQuery: new DtQuery(),
      dtSpinShow: false,
      dtPageShow: true,
      dtEditModalShow: false,
      dtSubmitting: false,
      dtContinue: false,
      dtDetailDrawerShow: false,
      dtQueryPoptipShow: false,
      dtQuerySubmitting: false,
      dtKeySearchSubmitting: false,
      dtKeySearchClickRate: 0,
      sortColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '名称', key: 'columnNameAnnotation', minWidth: 130 },
        { title: '列名', key: 'columnName', minWidth: 220 },
        { title: '排序规则', key: 'sortRule', width: 100 }
      ],
      sortFormValidate: {
        sortRule: [
          { required: true, message: '排序规则不能为空', trigger: 'blur' }
        ],
        dtId: [
          { required: true, message: '数据库表编号不能为空', trigger: 'blur' }
        ],
        columnsId: [
          { required: true, message: '列编号不能为空', trigger: 'blur' }
        ]
      },
      sortSet: [],
      sortSelectRow: [],
      sortSelectRowLength: 0,
      sort: new Sort(),
      sortVo: new SortVO(),
      dtListOfSortEdit: [],
      columnsListOfSortEdit: [],
      columnsSelectDisabledOfSortEdit: false,
      sortSpinShow: false,
      sortEditModalShow: false,
      sortSubmitting: false,
      sortContinue: false,
      sortDetailDrawerShow: false,
      virtualColumnsColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '别名', key: 'displayColumnsAlias', minWidth: 220 },
        { title: '关联列名', key: 'columnName', minWidth: 220 },
        { title: '关联表名', key: 'dtName', minWidth: 200 },
        { title: '排序权重', key: 'weightOrder', width: 100 }
      ],
      virtualColumnsFormValidate: {
        sourceDtId: [
          { required: true, message: '源表数据库表编号不能为空', trigger: 'blur' }
        ],
        targetDtId: [
          { required: true, message: '目标表数据库表编号不能为空', trigger: 'blur' }
        ],
        sourceColumnsId: [
          { required: true, message: '源表列编号不能为空', trigger: 'blur' }
        ],
        targetColumnsId: [
          { required: true, message: '目标表列编号不能为空', trigger: 'blur' }
        ],
        targetDisplayColumnsId: [
          { required: true, message: '目标表显示字段列编号不能为空', trigger: 'blur' }
        ],
        displayColumnsAlias: [
          { required: true, message: '显示字段别名不能为空', trigger: 'blur' }
        ],
        virtualColumnsSql: [
          { required: true, message: '虚拟列sql不能为空', trigger: 'blur' }
        ]
      },
      virtualColumnsSet: [],
      virtualColumnsSelectRow: [],
      virtualColumnsSelectRowLength: 0,
      virtualColumns: new VirtualColumns(),
      virtualColumnsVo: new VirtualColumnsVO(),
      sourceDtList: [],
      sourceColumnsList: [],
      targetDtList: [],
      targetColumnsList: [],
      targetDisplayColumnsList: [],
      virtualColumnsSpinShow: false,
      virtualColumnsEditModalShow: false,
      virtualColumnsSubmitting: false,
      virtualColumnsContinue: false,
      virtualColumnsDetailDrawerShow: false
    }
  },
  computed: {
    ...mapState('dtStore', [
    ]),
    ...mapGetters('dtStore', [
    ]),
    ...mapState('sortStore', [
    ]),
    ...mapGetters('sortStore', [
    ]),
    ...mapState('virtualColumnsStore', [
    ]),
    ...mapGetters('virtualColumnsStore', [
    ])
  },
  methods: {
    ...mapMutations('dtStore', [
    ]),
    ...mapActions('dtStore', [
      'saveDt',
      'updateDt',
      'batchRemoveDt',
      'getDtByPk',
      'getAllDt',
      'queryDt',
      'pagingQueryDt'
    ]),
    ...mapMutations('sortStore', [
    ]),
    ...mapActions('sortStore', [
      'saveSort',
      'updateSort',
      'batchRemoveSort',
      'getSortByPk',
      'getSortVOByPk',
      'querySort',
      'pagingQuerySort',
      'querySortVO'
    ]),
    ...mapMutations('virtualColumnsStore', [
    ]),
    ...mapActions('virtualColumnsStore', [
      'saveVirtualColumns',
      'updateVirtualColumns',
      'batchRemoveVirtualColumns',
      'getVirtualColumnsByPk',
      'getVirtualColumnsVOByPk',
      'queryVirtualColumns',
      'pagingQueryVirtualColumns',
      'queryVirtualColumnsVO'
    ]),
    ...mapActions('columnsStore', [
      'getColumnsByPk',
      'queryColumns'
    ]),
    ...mapActions('codingStore', [
      'extract'
    ]),
    clickDtRow (rowData) {
      this.currentDtIdOfClickDt = rowData.dtId
      if (this.currentTabName === 'sort') {
        this.sortSpinShow = true
        let sortQuery = new SortQuery()
        sortQuery.dtId = this.currentDtIdOfClickDt
        this.querySortVO(sortQuery).then(res => {
          if (res.data.success) {
            this.sortSet = res.data.data
            this.setSortSelectRow([])
            setTimeout(() => { this.sortSpinShow = false }, 700)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.sortSpinShow = false }, 700)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.sortSpinShow = false }, 700)
        })
      } else if (this.currentTabName === 'virtualColumns') {
        this.virtualColumnsSpinShow = true
        let virtualColumnsQuery = new VirtualColumnsQuery()
        virtualColumnsQuery.sourceDtId = this.currentDtIdOfClickDt
        this.queryVirtualColumnsVO(virtualColumnsQuery).then(res => {
          if (res.data.success) {
            this.virtualColumnsSet = res.data.data
            this.setVirtualColumnsSelectRow([])
            setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
        })
      }
    },
    changeTabName (name) {
      if (this.currentDtIdOfClickDt) {
        if (name === 'sort') {
          this.sortSpinShow = true
          let sortQuery = new SortQuery()
          sortQuery.dtId = this.currentDtIdOfClickDt
          this.querySortVO(sortQuery).then(res => {
            if (res.data.success) {
              this.sortSet = res.data.data
              this.setSortSelectRow([])
              setTimeout(() => { this.sortSpinShow = false }, 700)
            } else {
              this.$Message.error({ content: res.data.msg, duration: 6 })
              setTimeout(() => { this.sortSpinShow = false }, 700)
            }
          }).catch(result => {
            this.$Message.error({ content: result.response.statusText, duration: 6 })
            setTimeout(() => { this.sortSpinShow = false }, 700)
          })
        } else if (name === 'virtualColumns') {
          this.virtualColumnsSpinShow = true
          let virtualColumnsQuery = new VirtualColumnsQuery()
          virtualColumnsQuery.sourceDtId = this.currentDtIdOfClickDt
          this.queryVirtualColumnsVO(virtualColumnsQuery).then(res => {
            if (res.data.success) {
              this.virtualColumnsSet = res.data.data
              this.setVirtualColumnsSelectRow([])
              setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
            } else {
              this.$Message.error({ content: res.data.msg, duration: 6 })
              setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
            }
          }).catch(result => {
            this.$Message.error({ content: result.response.statusText, duration: 6 })
            setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
          })
        }
      }
    },
    addDt () {
      this.dtEditModalShow = true
      this.dt = new Dt()
    },
    upDt () {
      this.dtEditModalShow = true
      let dtEdit = new Dt()
      dtEdit.dtId = this.dtSelectRow[0].dtId
      this.getDtByPk(dtEdit).then(res => {
        if (res.data.success) {
          Object.assign(this.dt, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toDtList () {
      this.dtEditModalShow = false
      this.resetDtForm()
      this.getDtSet()
    },
    resetDtForm () {
      this.$refs.dtForm.resetFields()
    },
    saveDtForm () {
      this.dtContinue = false
      this.submitDtForm()
    },
    saveAddDtForm () {
      this.dtContinue = true
      this.submitDtForm()
    },
    handleUpload (file) {
      let reader = new FileReader()
      reader.readAsText(file, 'UTF-8')
      reader.onload = evt => {
        let fileString = evt.target.result
        if (fileString.indexOf('CREATE TABLE') !== -1) {
          fileString = fileString.substring(fileString.indexOf('CREATE TABLE'))
          if (fileString.indexOf(';') !== -1) {
            this.dt.dtSql = fileString.substring(0, fileString.indexOf(';') + 1)
          }
        }
      }
      return false
    },
    submitDtForm () {
      this.dtSubmitting = true
      this.$refs.dtForm.validate((valid) => {
        if (valid) {
          if (this.dt.dtId) {
            this.updateDt(this.dt).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '数据库表信息修改成功.', duration: 3 })
                this.resetDtForm()
                this.dt = new Dt()
                this.dtSubmitting = false
                if (!this.dtContinue) {
                  this.toDtList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.dtSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.dtSubmitting = false }, 3000)
            })
          } else {
            this.saveDt(this.dt).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '数据库表信息保存成功.', duration: 3 })
                this.resetDtForm()
                this.dt = new Dt()
                this.dtSubmitting = false
                if (!this.dtContinue) {
                  this.toDtList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.dtSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.dtSubmitting = false }, 3000)
            })
          }
        } else {
          setTimeout(() => { this.dtSubmitting = false }, 3000)
        }
      })
    },
    delDt () {
      this.dtSpinShow = true
      this.batchRemoveDt(this.dtSelectRow).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '数据库表信息删除成功.', duration: 3 })
          this.setDtSelectRow([])
          this.getDtSet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.dtSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.dtSpinShow = false }, 700)
      })
    },
    detailDt () {
      this.dtDetailDrawerShow = true
      let dtDetail = new Dt()
      dtDetail.dtId = this.dtSelectRow[0].dtId
      this.getDtByPk(dtDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.dt, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    dblclickDetailDt (rowData) {
      this.dtDetailDrawerShow = true
      let dtDetail = new Dt()
      dtDetail.dtId = rowData.dtId
      this.getDtByPk(dtDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.dt, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getDtSet () {
      this.dtSpinShow = true
      this.dtPageShow = true
      this.pagingQueryDt({ dtQuery: {}, rows: this.dtRows, page: this.dtPage }).then(res => {
        if (res.data.success) {
          this.dtSet = res.data.data
          this.dtCount = res.data.count
          this.setDtSelectRow([])
          setTimeout(() => { this.dtSpinShow = false }, 700)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.dtSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.dtSpinShow = false }, 700)
      })
    },
    keySearchDt (keyLikeValue) {
      if (keyLikeValue !== '') {
        this.dtKeySearchSubmitting = true
        this.dtPageShow = false
        this.dtSpinShow = true
        let dtQueryKeySearch = new DtQuery()
        dtQueryKeySearch.dtNameAndKeyLike = '%' + keyLikeValue + '%'
        dtQueryKeySearch.dtNameAnnotationOrKeyLike = '%' + keyLikeValue + '%'
        dtQueryKeySearch.dtPrefixOrKeyLike = '%' + keyLikeValue + '%'
        dtQueryKeySearch.initialCaseEntityNameOrKeyLike = '%' + keyLikeValue + '%'
        dtQueryKeySearch.initialLowercaseEntityNameOrKeyLike = '%' + keyLikeValue + '%'
        dtQueryKeySearch.proPathOrKeyLike = '%' + keyLikeValue + '%'
        dtQueryKeySearch.proAllNameOrKeyLike = '%' + keyLikeValue + '%'
        this.queryDt(dtQueryKeySearch).then(res => {
          if (res.data.success) {
            this.dtSet = res.data.data
            this.dtCount = res.data.count
            this.setDtSelectRow([])
            setTimeout(() => { this.dtSpinShow = false }, 700)
            setTimeout(() => { this.dtKeySearchSubmitting = false }, 5000)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.dtSpinShow = false }, 700)
            setTimeout(() => { this.dtKeySearchSubmitting = false }, 5000)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.dtSpinShow = false }, 700)
          setTimeout(() => { this.dtKeySearchSubmitting = false }, 5000)
        })
      } else {
        this.dtKeySearchClickRate++
        if (this.dtKeySearchClickRate > 10) {
          this.$Message.warning({ content: '兄dei，你是闲的吗？', duration: 6 })
          this.dtKeySearchClickRate = 0
        }
      }
    },
    vagueQueryDt () {
      this.dtQuerySubmitting = true
      this.dtPageShow = false
      this.dtSpinShow = true
      this.dtQueryPoptipShow = false
      this.queryDt(this.dtQuery).then(res => {
        if (res.data.success) {
          this.dtSet = res.data.data
          this.dtCount = res.data.count
          this.setDtSelectRow([])
          setTimeout(() => { this.dtSpinShow = false }, 700)
          setTimeout(() => { this.dtQuerySubmitting = false }, 5000)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.dtSpinShow = false }, 700)
          setTimeout(() => { this.dtQuerySubmitting = false }, 5000)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.dtSpinShow = false }, 700)
        setTimeout(() => { this.dtQuerySubmitting = false }, 5000)
      })
    },
    resetDtQueryForm () {
      this.$refs.dtQueryForm.resetFields()
    },
    setDtSelectRow (list) {
      this.dtSelectRow = list
      this.dtSelectRowLength = list.length
    },
    dtPageIndexChange (pageIndex) {
      this.dtPage = pageIndex
      this.getDtSet()
    },
    dtPageSizeChange (pageSize) {
      this.dtRows = pageSize
      this.dtPage = 1
      this.getDtSet()
    },
    dtRefresh () {
      this.dtPage = 1
      this.getDtSet()
    },
    extractData () {
      this.dtSpinShow = true
      this.extract(this.dtSelectRow[0]).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '数据库表信息提取成功.', duration: 3 })
          this.setDtSelectRow([])
          this.getDtSet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.dtSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.dtSpinShow = false }, 700)
      })
    },
    addSort () {
      if (this.currentDtIdOfClickDt) {
        this.sortEditModalShow = true
        this.columnsSelectDisabledOfSortEdit = false
        this.sort = new Sort()
        this.sort.dtId = this.currentDtIdOfClickDt
        this.getAllDt().then(res => {
          if (res.data.success) {
            this.dtListOfSortEdit = res.data.data
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
        })
        let columnsQueryOfSortEdit = new ColumnsQuery()
        columnsQueryOfSortEdit.dtId = this.currentDtIdOfClickDt
        this.queryColumns(columnsQueryOfSortEdit).then(res => {
          if (res.data.success) {
            this.columnsListOfSortEdit = res.data.data
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
        })
      } else {
        this.sortEditModalShow = true
        this.columnsSelectDisabledOfSortEdit = true
        this.sort = new Sort()
        this.getAllDt().then(res => {
          if (res.data.success) {
            this.dtListOfSortEdit = res.data.data
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
        })
      }
    },
    upSort () {
      this.sortEditModalShow = true
      this.columnsSelectDisabledOfSortEdit = false
      let sortEdit = new Sort()
      sortEdit.sortId = this.sortSelectRow[0].sortId
      this.getSortByPk(sortEdit).then(res => {
        if (res.data.success) {
          Object.assign(this.sort, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
      this.getAllDt().then(res => {
        if (res.data.success) {
          this.dtListOfSortEdit = res.data.data
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
      let columnsQueryOfSortEdit = new ColumnsQuery()
      columnsQueryOfSortEdit.dtId = this.sortSelectRow[0].dtId
      this.queryColumns(columnsQueryOfSortEdit).then(res => {
        if (res.data.success) {
          this.columnsListOfSortEdit = res.data.data
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toSortList () {
      this.sortEditModalShow = false
      this.resetSortForm()
      this.getSortSet()
    },
    resetSortForm () {
      this.$refs.sortForm.resetFields()
    },
    saveSortForm () {
      this.sortContinue = false
      this.submitSortForm()
    },
    saveAddSortForm () {
      this.sortContinue = true
      this.submitSortForm()
    },
    changeDtOfSortEdit (value) {
      if (value) {
        let columnsQuery = new ColumnsQuery()
        columnsQuery.dtId = value
        this.queryColumns(columnsQuery).then(res => {
          if (res.data.success) {
            this.columnsSelectDisabledOfSortEdit = false
            this.sort.columnsId = null
            this.columnsListOfSortEdit = res.data.data
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
        })
      }
    },
    submitSortForm () {
      this.sortSubmitting = true
      this.$refs.sortForm.validate((valid) => {
        if (valid) {
          if (this.sort.sortId) {
            this.updateSort(this.sort).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '排序信息修改成功.', duration: 3 })
                this.resetSortForm()
                this.sort = new Sort()
                this.sortSubmitting = false
                if (!this.sortContinue) {
                  this.toSortList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.sortSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.sortSubmitting = false }, 3000)
            })
          } else {
            this.saveSort(this.sort).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '排序信息保存成功.', duration: 3 })
                this.resetSortForm()
                this.sort = new Sort()
                this.sortSubmitting = false
                if (!this.sortContinue) {
                  this.toSortList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.sortSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.sortSubmitting = false }, 3000)
            })
          }
        } else {
          setTimeout(() => { this.sortSubmitting = false }, 3000)
        }
      })
    },
    delSort () {
      this.sortSpinShow = true
      this.batchRemoveSort(this.sortSelectRow).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '排序信息删除成功.', duration: 3 })
          this.setSortSelectRow([])
          this.getSortSet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.sortSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.sortSpinShow = false }, 700)
      })
    },
    detailSort () {
      this.sortDetailDrawerShow = true
      let sortDetail = new Sort()
      sortDetail.sortId = this.sortSelectRow[0].sortId
      this.getSortVOByPk(sortDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.sortVo, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    dblclickDetailSort (rowData) {
      this.sortDetailDrawerShow = true
      let sortDetail = new Sort()
      sortDetail.sortId = rowData.sortId
      this.getSortVOByPk(sortDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.sortVo, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getSortSet () {
      if (this.currentDtIdOfClickDt) {
        this.sortSpinShow = true
        let sortQuery = new SortQuery()
        sortQuery.dtId = this.currentDtIdOfClickDt
        this.querySortVO(sortQuery).then(res => {
          if (res.data.success) {
            this.sortSet = res.data.data
            this.setSortSelectRow([])
            setTimeout(() => { this.sortSpinShow = false }, 700)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.sortSpinShow = false }, 700)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.sortSpinShow = false }, 700)
        })
      }
    },
    setSortSelectRow (list) {
      this.sortSelectRow = list
      this.sortSelectRowLength = list.length
    },
    sortRefresh () {
      this.getSortSet()
    },
    addVirtualColumns () {
      this.virtualColumnsEditModalShow = true
      this.virtualColumns = new VirtualColumns()
      this.getAllDt().then(res => {
        if (res.data.success) {
          this.sourceDtList = res.data.data
          this.targetDtList = res.data.data
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toVirtualColumnsList () {
      this.virtualColumnsEditModalShow = false
      this.resetVirtualColumnsForm()
      this.getVirtualColumnsSet()
    },
    resetVirtualColumnsForm () {
      this.$refs.virtualColumnsForm.resetFields()
    },
    saveVirtualColumnsForm () {
      this.virtualColumnsContinue = false
      this.submitVirtualColumnsForm()
    },
    saveAddVirtualColumnsForm () {
      this.virtualColumnsContinue = true
      this.submitVirtualColumnsForm()
    },
    changeSourceDt (value) {
      if (value) {
        let columnsQuery = new ColumnsQuery()
        columnsQuery.dtId = value
        this.queryColumns(columnsQuery).then(res => {
          if (res.data.success) {
            this.virtualColumns.sourceColumnsId = null
            this.sourceColumnsList = res.data.data
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
        })
      }
    },
    changeTargetDt (value) {
      if (value) {
        let columnsQuery = new ColumnsQuery()
        columnsQuery.dtId = value
        this.queryColumns(columnsQuery).then(res => {
          if (res.data.success) {
            this.virtualColumns.targetColumnsId = null
            this.virtualColumns.targetDisplayColumnsId = null
            this.targetColumnsList = res.data.data
            this.targetDisplayColumnsList = res.data.data
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
        })
      }
    },
    changeDisplayColumns (value) {
      if (value) {
        let columns = new Columns()
        columns.columnsId = value
        this.getColumnsByPk(columns).then(res => {
          if (res.data.success) {
            this.virtualColumns.displayColumnsAlias = res.data.data[0].columnName
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
        })
      }
    },
    submitVirtualColumnsForm () {
      this.virtualColumnsSubmitting = true
      this.$refs.virtualColumnsForm.validate((valid) => {
        if (valid) {
          if (this.virtualColumns.virtualColumnsId) {
            this.updateVirtualColumns(this.virtualColumns).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '虚拟列信息修改成功.', duration: 3 })
                this.resetVirtualColumnsForm()
                this.virtualColumns = new VirtualColumns()
                this.virtualColumnsSubmitting = false
                if (!this.virtualColumnsContinue) {
                  this.toVirtualColumnsList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.virtualColumnsSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.virtualColumnsSubmitting = false }, 3000)
            })
          } else {
            this.saveVirtualColumns(this.virtualColumns).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '虚拟列信息保存成功.', duration: 3 })
                this.resetVirtualColumnsForm()
                this.virtualColumns = new VirtualColumns()
                this.virtualColumnsSubmitting = false
                if (!this.virtualColumnsContinue) {
                  this.toVirtualColumnsList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.virtualColumnsSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.virtualColumnsSubmitting = false }, 3000)
            })
          }
        } else {
          setTimeout(() => { this.virtualColumnsSubmitting = false }, 3000)
        }
      })
    },
    delVirtualColumns () {
      this.virtualColumnsSpinShow = true
      this.batchRemoveVirtualColumns(this.virtualColumnsSelectRow).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '虚拟列信息删除成功.', duration: 3 })
          this.setVirtualColumnsSelectRow([])
          this.getVirtualColumnsSet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
      })
    },
    detailVirtualColumns () {
      this.virtualColumnsDetailDrawerShow = true
      let virtualColumnsDetail = new VirtualColumns()
      virtualColumnsDetail.virtualColumnsId = this.virtualColumnsSelectRow[0].virtualColumnsId
      this.getVirtualColumnsVOByPk(virtualColumnsDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.virtualColumnsVo, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    dblclickDetailVirtualColumns (rowData) {
      this.virtualColumnsDetailDrawerShow = true
      let virtualColumnsDetail = new VirtualColumns()
      virtualColumnsDetail.virtualColumnsId = rowData.virtualColumnsId
      this.getVirtualColumnsVOByPk(virtualColumnsDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.virtualColumnsVo, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getVirtualColumnsSet () {
      if (this.currentDtIdOfClickDt) {
        this.virtualColumnsSpinShow = true
        let virtualColumnsQuery = new VirtualColumnsQuery()
        virtualColumnsQuery.sourceDtId = this.currentDtIdOfClickDt
        this.queryVirtualColumnsVO(virtualColumnsQuery).then(res => {
          if (res.data.success) {
            this.virtualColumnsSet = res.data.data
            this.setVirtualColumnsSelectRow([])
            setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
        })
      }
    },
    setVirtualColumnsSelectRow (list) {
      this.virtualColumnsSelectRow = list
      this.virtualColumnsSelectRowLength = list.length
    },
    virtualColumnsRefresh () {
      this.getVirtualColumnsSet()
    }
  },
  mounted () {
    this.getDtSet()
  }
}
</script>
