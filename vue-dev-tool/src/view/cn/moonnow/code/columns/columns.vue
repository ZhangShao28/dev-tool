<style lang="less" scoped>
  @import './columns.less';
</style>

<template>
  <div>
    <Card>
      <Row>
        <Col :xs="16" :sm="14" :md="10" :lg="6">
          <Input placeholder="关键字搜索" search enter-button :disabled="columnsKeySearchSubmitting" @on-search="keySearchColumns($event)" />
        </Col>
        <Col :xs="8" :sm="10" :md="14" :lg="18">
          <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="900" v-model="columnsQueryPoptipShow">
            <Button icon="ios-locate-outline">精准查询</Button>
            <div slot="content">
              <Form class="pro-margin-top-10" ref="columnsQueryForm" :model="columnsQuery" :label-width="120">
                <Row>
                  <Col span="8">
                    <FormItem label="列名：" prop="columnName">
                      <Input v-model="columnsQuery.columnName" placeholder="输入列名"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="列名注释：" prop="columnNameAnnotation">
                      <Input v-model="columnsQuery.columnNameAnnotation" placeholder="输入列名注释"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="数据类型：" prop="dataType">
                      <Input v-model="columnsQuery.dataType" placeholder="输入数据类型"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="是否为空：" prop="isNull">
                      <Input v-model="columnsQuery.isNull" placeholder="输入是否为空"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="首字母大写列名：" prop="initialCaseColumnName">
                      <Input v-model="columnsQuery.initialCaseColumnName" placeholder="输入首字母大写列名"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="首字母小写列名：" prop="initialLowercaseColumnName">
                      <Input v-model="columnsQuery.initialLowercaseColumnName" placeholder="输入首字母小写列名"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="排序权重：" prop="weightOrder">
                      <InputNumber v-model="columnsQuery.weightOrder"></InputNumber>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="数据库表编号：" prop="dtId">
                      <Input v-model="columnsQuery.dtId" placeholder="输入数据库表编号"></Input>
                    </FormItem>
                  </Col>
                </Row>
                <Divider />
                <Row>
                  <Col span="24">
                    <FormItem class="pro-float-right">
                      <Button type="primary" @click="vagueQueryColumns()" :loading="columnsQuerySubmitting">精准查询</Button>
                      <Button class="pro-margin-left-8" @click="resetColumnsQueryForm()">重置</Button>
                    </FormItem>
                  </Col>
                </Row>
              </Form>
            </div>
          </Poptip>
        </Col>
      </Row>
      <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addColumns()">新建</Button>
      <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upColumns()" v-if="columnsSelectRowLength === 1">编辑</Button>
      <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delColumns()" v-if="columnsSelectRowLength > 0">
        <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
      </Poptip>
      <Button class="pro-list-button" icon="md-bulb" @click="detailColumns()" v-if="columnsSelectRowLength === 1">详情</Button>
      <Table :columns="columnsColumns" :data="columnsSet" @on-selection-change="setColumnsSelectRow($event)" @on-row-dblclick="dblclickDetailColumns($event)"></Table>
      <Row class="pro-margin-top-10" type="flex" justify="center">
        <Page size="small" placement="top" show-total show-sizer show-elevator :total="columnsCount" :current="columnsPage" :page-size-opts="[10, 30, 50]" @on-change="columnsPageIndexChange($event)" @on-page-size-change="columnsPageSizeChange($event)" v-if="columnsPageShow" />
        <Button type="text" shape="circle" icon="md-refresh" @click="columnsRefresh()"></Button>
      </Row>
      <Spin size="large" fix v-if="columnsSpinShow"></Spin>
    </Card>
    <Modal width="60" v-model="columnsEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑列
        </p>
        <Form class="pro-margin-top-20" ref="columnsForm" :model="columns" :rules="columnsFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="列名：" prop="columnName">
                <Input v-model="columns.columnName" placeholder="输入列名"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="列名注释：" prop="columnNameAnnotation">
                <Input v-model="columns.columnNameAnnotation" placeholder="输入列名注释"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="数据类型：" prop="dataType">
                <Input v-model="columns.dataType" placeholder="输入数据类型"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="是否为空：" prop="isNull">
                <Input v-model="columns.isNull" placeholder="输入是否为空"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="首字母大写列名：" prop="initialCaseColumnName">
                <Input v-model="columns.initialCaseColumnName" placeholder="输入首字母大写列名"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="首字母小写列名：" prop="initialLowercaseColumnName">
                <Input v-model="columns.initialLowercaseColumnName" placeholder="输入首字母小写列名"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="排序权重：" prop="weightOrder">
                <InputNumber v-model="columns.weightOrder"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="数据库表编号：" prop="dtId">
                <Input v-model="columns.dtId" placeholder="输入数据库表编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem>
                <Button class="pro-margin-right-8" @click="toColumnsList()">取消</Button>
                <Button class="pro-margin-right-8" @click="resetColumnsForm()">重置</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="columnsSubmitting" @click="saveColumnsForm()">保存</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="columnsSubmitting" @click="saveAddColumnsForm()">继续录入</Button>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    </Modal>
    <Drawer width="45" v-model="columnsDetailDrawerShow" :closable="false">
      <Card>
        <p slot="title">
          <Icon type="md-bulb"></Icon>
          列详情
        </p>
        <Row>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>列编号：{{ columns.columnsId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>列名：{{ columns.columnName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>列名注释：{{ columns.columnNameAnnotation }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据类型：{{ columns.dataType }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>是否为空：{{ columns.isNull }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>首字母大写列名：{{ columns.initialCaseColumnName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>首字母小写列名：{{ columns.initialLowercaseColumnName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>排序权重：{{ columns.weightOrder }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据库表编号：{{ columns.dtId }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import Columns from '@/view/cn/moonnow/code/columns/Columns'
import ColumnsQuery from '@/view/cn/moonnow/code/columns/ColumnsQuery'

export default {
  data () {
    return {
      columnsColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '列名', key: 'columnName', minWidth: 200 },
        { title: '列名注释', key: 'columnNameAnnotation', minWidth: 200 },
        { title: '数据类型', key: 'dataType', minWidth: 200 },
        { title: '是否为空', key: 'isNull', minWidth: 200 },
        { title: '首字母大写列名', key: 'initialCaseColumnName', minWidth: 200 },
        { title: '首字母小写列名', key: 'initialLowercaseColumnName', minWidth: 200 },
        { title: '排序权重', key: 'weightOrder', minWidth: 200 },
        { title: '数据库表编号', key: 'dtId', minWidth: 200 }
      ],
      columnsFormValidate: {
        dtId: [
          { required: true, message: '数据库表编号不能为空', trigger: 'blur' }
        ]
      },
      columnsSet: [],
      columnsCount: 0,
      columnsRows: 10,
      columnsPage: 1,
      columnsSelectRow: [],
      columnsSelectRowLength: 0,
      columns: new Columns(),
      columnsQuery: new ColumnsQuery(),
      columnsSpinShow: false,
      columnsPageShow: true,
      columnsEditModalShow: false,
      columnsSubmitting: false,
      columnsContinue: false,
      columnsDetailDrawerShow: false,
      columnsQueryPoptipShow: false,
      columnsQuerySubmitting: false,
      columnsKeySearchSubmitting: false,
      columnsKeySearchClickRate: 0
    }
  },
  computed: {
    ...mapState('columnsStore', [
    ]),
    ...mapGetters('columnsStore', [
    ])
  },
  methods: {
    ...mapMutations('columnsStore', [
    ]),
    ...mapActions('columnsStore', [
      'saveColumns',
      'updateColumns',
      'batchRemoveColumns',
      'getColumnsByPk',
      'queryColumns',
      'pagingQueryColumns'
    ]),
    addColumns () {
      this.columnsEditModalShow = true
      this.columns = new Columns()
    },
    upColumns () {
      this.columnsEditModalShow = true
      let columnsEdit = new Columns()
      columnsEdit.columnsId = this.columnsSelectRow[0].columnsId
      this.getColumnsByPk(columnsEdit).then(res => {
        if (res.data.success) {
          Object.assign(this.columns, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toColumnsList () {
      this.columnsEditModalShow = false
      this.resetColumnsForm()
      this.getColumnsSet()
    },
    resetColumnsForm () {
      this.$refs.columnsForm.resetFields()
    },
    saveColumnsForm () {
      this.columnsContinue = false
      this.submitColumnsForm()
    },
    saveAddColumnsForm () {
      this.columnsContinue = true
      this.submitColumnsForm()
    },
    submitColumnsForm () {
      this.columnsSubmitting = true
      this.$refs.columnsForm.validate((valid) => {
        if (valid) {
          if (this.columns.columnsId) {
            this.updateColumns(this.columns).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '列信息修改成功.', duration: 3 })
                this.resetColumnsForm()
                this.columns = new Columns()
                this.columnsSubmitting = false
                if (!this.columnsContinue) {
                  this.toColumnsList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.columnsSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.columnsSubmitting = false }, 3000)
            })
          } else {
            this.saveColumns(this.columns).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '列信息保存成功.', duration: 3 })
                this.resetColumnsForm()
                this.columns = new Columns()
                this.columnsSubmitting = false
                if (!this.columnsContinue) {
                  this.toColumnsList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.columnsSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.columnsSubmitting = false }, 3000)
            })
          }
        } else {
          setTimeout(() => { this.columnsSubmitting = false }, 3000)
        }
      })
    },
    delColumns () {
      this.columnsSpinShow = true
      this.batchRemoveColumns(this.columnsSelectRow).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '列信息删除成功.', duration: 3 })
          this.setColumnsSelectRow([])
          this.getColumnsSet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.columnsSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.columnsSpinShow = false }, 700)
      })
    },
    detailColumns () {
      this.columnsDetailDrawerShow = true
      let columnsDetail = new Columns()
      columnsDetail.columnsId = this.columnsSelectRow[0].columnsId
      this.getColumnsByPk(columnsDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.columns, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    dblclickDetailColumns (rowData) {
      this.columnsDetailDrawerShow = true
      let columnsDetail = new Columns()
      columnsDetail.columnsId = rowData.columnsId
      this.getColumnsByPk(columnsDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.columns, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getColumnsSet () {
      this.columnsSpinShow = true
      this.columnsPageShow = true
      this.pagingQueryColumns({ columnsQuery: {}, rows: this.columnsRows, page: this.columnsPage }).then(res => {
        if (res.data.success) {
          this.columnsSet = res.data.data
          this.columnsCount = res.data.count
          this.setColumnsSelectRow([])
          setTimeout(() => { this.columnsSpinShow = false }, 700)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.columnsSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.columnsSpinShow = false }, 700)
      })
    },
    keySearchColumns (keyLikeValue) {
      if (keyLikeValue !== '') {
        this.columnsKeySearchSubmitting = true
        this.columnsPageShow = false
        this.columnsSpinShow = true
        let columnsQueryKeySearch = new ColumnsQuery()
        columnsQueryKeySearch.columnNameAndKeyLike = '%' + keyLikeValue + '%'
        columnsQueryKeySearch.columnNameAnnotationOrKeyLike = '%' + keyLikeValue + '%'
        columnsQueryKeySearch.dataTypeOrKeyLike = '%' + keyLikeValue + '%'
        columnsQueryKeySearch.isNullOrKeyLike = '%' + keyLikeValue + '%'
        columnsQueryKeySearch.initialCaseColumnNameOrKeyLike = '%' + keyLikeValue + '%'
        columnsQueryKeySearch.initialLowercaseColumnNameOrKeyLike = '%' + keyLikeValue + '%'
        columnsQueryKeySearch.dtIdOrKeyLike = '%' + keyLikeValue + '%'
        this.queryColumns(columnsQueryKeySearch).then(res => {
          if (res.data.success) {
            this.columnsSet = res.data.data
            this.columnsCount = res.data.count
            this.setColumnsSelectRow([])
            setTimeout(() => { this.columnsSpinShow = false }, 700)
            setTimeout(() => { this.columnsKeySearchSubmitting = false }, 5000)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.columnsSpinShow = false }, 700)
            setTimeout(() => { this.columnsKeySearchSubmitting = false }, 5000)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.columnsSpinShow = false }, 700)
          setTimeout(() => { this.columnsKeySearchSubmitting = false }, 5000)
        })
      } else {
        this.columnsKeySearchClickRate++
        if (this.columnsKeySearchClickRate > 10) {
          this.$Message.warning({ content: '兄dei，你是闲的吗？', duration: 6 })
          this.columnsKeySearchClickRate = 0
        }
      }
    },
    vagueQueryColumns () {
      this.columnsQuerySubmitting = true
      this.columnsPageShow = false
      this.columnsSpinShow = true
      this.columnsQueryPoptipShow = false
      this.queryColumns(this.columnsQuery).then(res => {
        if (res.data.success) {
          this.columnsSet = res.data.data
          this.columnsCount = res.data.count
          this.setColumnsSelectRow([])
          setTimeout(() => { this.columnsSpinShow = false }, 700)
          setTimeout(() => { this.columnsQuerySubmitting = false }, 5000)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.columnsSpinShow = false }, 700)
          setTimeout(() => { this.columnsQuerySubmitting = false }, 5000)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.columnsSpinShow = false }, 700)
        setTimeout(() => { this.columnsQuerySubmitting = false }, 5000)
      })
    },
    resetColumnsQueryForm () {
      this.$refs.columnsQueryForm.resetFields()
    },
    setColumnsSelectRow (list) {
      this.columnsSelectRow = list
      this.columnsSelectRowLength = list.length
    },
    columnsPageIndexChange (pageIndex) {
      this.columnsPage = pageIndex
      this.getColumnsSet()
    },
    columnsPageSizeChange (pageSize) {
      this.columnsRows = pageSize
      this.columnsPage = 1
      this.getColumnsSet()
    },
    columnsRefresh () {
      this.columnsPage = 1
      this.getColumnsSet()
    }
  },
  mounted () {
    this.getColumnsSet()
  }
}
</script>
