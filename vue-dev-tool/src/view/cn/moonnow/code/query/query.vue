<style lang="less" scoped>
  @import './query.less';
</style>

<template>
  <div>
    <Card>
      <Row>
        <Col :xs="16" :sm="14" :md="10" :lg="6">
          <Input placeholder="关键字搜索" search enter-button :disabled="queryKeySearchSubmitting" @on-search="keySearchQuery($event)" />
        </Col>
        <Col :xs="8" :sm="10" :md="14" :lg="18">
          <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="900" v-model="queryQueryPoptipShow">
            <Button icon="ios-locate-outline">精准查询</Button>
            <div slot="content">
              <Form class="pro-margin-top-10" ref="queryQueryForm" :model="queryQuery" :label-width="120">
                <Row>
                  <Col span="8">
                    <FormItem label="查询类型：" prop="queryType">
                      <Input v-model="queryQuery.queryType" placeholder="输入查询类型"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="数据库表编号：" prop="dtId">
                      <Input v-model="queryQuery.dtId" placeholder="输入数据库表编号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="列编号：" prop="columnsId">
                      <Input v-model="queryQuery.columnsId" placeholder="输入列编号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="排序权重：" prop="weightOrder">
                      <InputNumber v-model="queryQuery.weightOrder"></InputNumber>
                    </FormItem>
                  </Col>
                </Row>
                <Divider />
                <Row>
                  <Col span="24">
                    <FormItem class="pro-float-right">
                      <Button type="primary" @click="vagueQueryQuery()" :loading="queryQuerySubmitting">精准查询</Button>
                      <Button class="pro-margin-left-8" @click="resetQueryQueryForm()">重置</Button>
                    </FormItem>
                  </Col>
                </Row>
              </Form>
            </div>
          </Poptip>
        </Col>
      </Row>
      <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addQuery()">新建</Button>
      <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upQuery()" v-if="querySelectRowLength === 1">编辑</Button>
      <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delQuery()" v-if="querySelectRowLength > 0">
        <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
      </Poptip>
      <Button class="pro-list-button" icon="md-bulb" @click="detailQuery()" v-if="querySelectRowLength === 1">详情</Button>
      <Table :columns="queryColumns" :data="querySet" @on-selection-change="setQuerySelectRow($event)" @on-row-dblclick="dblclickDetailQuery($event)"></Table>
      <Row class="pro-margin-top-10" type="flex" justify="center">
        <Page size="small" placement="top" show-total show-sizer show-elevator :total="queryCount" :current="queryPage" :page-size-opts="[10, 30, 50]" @on-change="queryPageIndexChange($event)" @on-page-size-change="queryPageSizeChange($event)" v-if="queryPageShow" />
        <Button type="text" shape="circle" icon="md-refresh" @click="queryRefresh()"></Button>
      </Row>
      <Spin size="large" fix v-if="querySpinShow"></Spin>
    </Card>
    <Modal width="60" v-model="queryEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑查询
        </p>
        <Form class="pro-margin-top-20" ref="queryForm" :model="query" :rules="queryFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="查询类型：" prop="queryType">
                <Input v-model="query.queryType" placeholder="输入查询类型"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="数据库表编号：" prop="dtId">
                <Input v-model="query.dtId" placeholder="输入数据库表编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="列编号：" prop="columnsId">
                <Input v-model="query.columnsId" placeholder="输入列编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="排序权重：" prop="weightOrder">
                <InputNumber v-model="query.weightOrder"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem>
                <Button class="pro-margin-right-8" @click="toQueryList()">取消</Button>
                <Button class="pro-margin-right-8" @click="resetQueryForm()">重置</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="querySubmitting" @click="saveQueryForm()">保存</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="querySubmitting" @click="saveAddQueryForm()">继续录入</Button>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    </Modal>
    <Drawer width="45" v-model="queryDetailDrawerShow" :closable="false">
      <Card>
        <p slot="title">
          <Icon type="md-bulb"></Icon>
          查询详情
        </p>
        <Row>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>查询编号：{{ query.queryId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>查询类型：{{ query.queryType }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据库表编号：{{ query.dtId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>列编号：{{ query.columnsId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>排序权重：{{ query.weightOrder }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import Query from '@/view/cn/moonnow/code/query/Query'
import QueryQuery from '@/view/cn/moonnow/code/query/QueryQuery'

export default {
  data () {
    return {
      queryColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '查询类型', key: 'queryType', minWidth: 200 },
        { title: '数据库表编号', key: 'dtId', minWidth: 200 },
        { title: '列编号', key: 'columnsId', minWidth: 200 },
        { title: '排序权重', key: 'weightOrder', minWidth: 200 }
      ],
      queryFormValidate: {
        queryType: [
          { required: true, message: '查询类型不能为空', trigger: 'blur' }
        ],
        dtId: [
          { required: true, message: '数据库表编号不能为空', trigger: 'blur' }
        ],
        columnsId: [
          { required: true, message: '列编号不能为空', trigger: 'blur' }
        ]
      },
      querySet: [],
      queryCount: 0,
      queryRows: 10,
      queryPage: 1,
      querySelectRow: [],
      querySelectRowLength: 0,
      query: new Query(),
      queryQuery: new QueryQuery(),
      querySpinShow: false,
      queryPageShow: true,
      queryEditModalShow: false,
      querySubmitting: false,
      queryContinue: false,
      queryDetailDrawerShow: false,
      queryQueryPoptipShow: false,
      queryQuerySubmitting: false,
      queryKeySearchSubmitting: false,
      queryKeySearchClickRate: 0
    }
  },
  computed: {
    ...mapState('queryStore', [
    ]),
    ...mapGetters('queryStore', [
    ])
  },
  methods: {
    ...mapMutations('queryStore', [
    ]),
    ...mapActions('queryStore', [
      'saveQuery',
      'updateQuery',
      'batchRemoveQuery',
      'getQueryByPk',
      'queryQuery',
      'pagingQueryQuery'
    ]),
    addQuery () {
      this.queryEditModalShow = true
      this.query = new Query()
    },
    upQuery () {
      this.queryEditModalShow = true
      let queryEdit = new Query()
      queryEdit.queryId = this.querySelectRow[0].queryId
      this.getQueryByPk(queryEdit).then(res => {
        if (res.data.success) {
          Object.assign(this.query, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toQueryList () {
      this.queryEditModalShow = false
      this.resetQueryForm()
      this.getQuerySet()
    },
    resetQueryForm () {
      this.$refs.queryForm.resetFields()
    },
    saveQueryForm () {
      this.queryContinue = false
      this.submitQueryForm()
    },
    saveAddQueryForm () {
      this.queryContinue = true
      this.submitQueryForm()
    },
    submitQueryForm () {
      this.querySubmitting = true
      this.$refs.queryForm.validate((valid) => {
        if (valid) {
          if (this.query.queryId) {
            this.updateQuery(this.query).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '查询信息修改成功.', duration: 3 })
                this.resetQueryForm()
                this.query = new Query()
                this.querySubmitting = false
                if (!this.queryContinue) {
                  this.toQueryList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.querySubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.querySubmitting = false }, 3000)
            })
          } else {
            this.saveQuery(this.query).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '查询信息保存成功.', duration: 3 })
                this.resetQueryForm()
                this.query = new Query()
                this.querySubmitting = false
                if (!this.queryContinue) {
                  this.toQueryList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.querySubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.querySubmitting = false }, 3000)
            })
          }
        } else {
          setTimeout(() => { this.querySubmitting = false }, 3000)
        }
      })
    },
    delQuery () {
      this.querySpinShow = true
      this.batchRemoveQuery(this.querySelectRow).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '查询信息删除成功.', duration: 3 })
          this.setQuerySelectRow([])
          this.getQuerySet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.querySpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.querySpinShow = false }, 700)
      })
    },
    detailQuery () {
      this.queryDetailDrawerShow = true
      let queryDetail = new Query()
      queryDetail.queryId = this.querySelectRow[0].queryId
      this.getQueryByPk(queryDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.query, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    dblclickDetailQuery (rowData) {
      this.queryDetailDrawerShow = true
      let queryDetail = new Query()
      queryDetail.queryId = rowData.queryId
      this.getQueryByPk(queryDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.query, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getQuerySet () {
      this.querySpinShow = true
      this.queryPageShow = true
      this.pagingQueryQuery({ queryQuery: {}, rows: this.queryRows, page: this.queryPage }).then(res => {
        if (res.data.success) {
          this.querySet = res.data.data
          this.queryCount = res.data.count
          this.setQuerySelectRow([])
          setTimeout(() => { this.querySpinShow = false }, 700)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.querySpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.querySpinShow = false }, 700)
      })
    },
    keySearchQuery (keyLikeValue) {
      if (keyLikeValue !== '') {
        this.queryKeySearchSubmitting = true
        this.queryPageShow = false
        this.querySpinShow = true
        let queryQueryKeySearch = new QueryQuery()
        queryQueryKeySearch.queryTypeAndKeyLike = '%' + keyLikeValue + '%'
        queryQueryKeySearch.dtIdOrKeyLike = '%' + keyLikeValue + '%'
        queryQueryKeySearch.columnsIdOrKeyLike = '%' + keyLikeValue + '%'
        this.queryQuery(queryQueryKeySearch).then(res => {
          if (res.data.success) {
            this.querySet = res.data.data
            this.queryCount = res.data.count
            this.setQuerySelectRow([])
            setTimeout(() => { this.querySpinShow = false }, 700)
            setTimeout(() => { this.queryKeySearchSubmitting = false }, 5000)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.querySpinShow = false }, 700)
            setTimeout(() => { this.queryKeySearchSubmitting = false }, 5000)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.querySpinShow = false }, 700)
          setTimeout(() => { this.queryKeySearchSubmitting = false }, 5000)
        })
      } else {
        this.queryKeySearchClickRate++
        if (this.queryKeySearchClickRate > 10) {
          this.$Message.warning({ content: '兄dei，你是闲的吗？', duration: 6 })
          this.queryKeySearchClickRate = 0
        }
      }
    },
    vagueQueryQuery () {
      this.queryQuerySubmitting = true
      this.queryPageShow = false
      this.querySpinShow = true
      this.queryQueryPoptipShow = false
      this.queryQuery(this.queryQuery).then(res => {
        if (res.data.success) {
          this.querySet = res.data.data
          this.queryCount = res.data.count
          this.setQuerySelectRow([])
          setTimeout(() => { this.querySpinShow = false }, 700)
          setTimeout(() => { this.queryQuerySubmitting = false }, 5000)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.querySpinShow = false }, 700)
          setTimeout(() => { this.queryQuerySubmitting = false }, 5000)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.querySpinShow = false }, 700)
        setTimeout(() => { this.queryQuerySubmitting = false }, 5000)
      })
    },
    resetQueryQueryForm () {
      this.$refs.queryQueryForm.resetFields()
    },
    setQuerySelectRow (list) {
      this.querySelectRow = list
      this.querySelectRowLength = list.length
    },
    queryPageIndexChange (pageIndex) {
      this.queryPage = pageIndex
      this.getQuerySet()
    },
    queryPageSizeChange (pageSize) {
      this.queryRows = pageSize
      this.queryPage = 1
      this.getQuerySet()
    },
    queryRefresh () {
      this.queryPage = 1
      this.getQuerySet()
    }
  },
  mounted () {
    this.getQuerySet()
  }
}
</script>
