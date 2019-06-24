<style lang="less" scoped>
  @import './coding.less';
</style>

<template>
  <div>
    <Card>
      <Row>
        <Col :xs="16" :sm="14" :md="10" :lg="6">
          <Input placeholder="关键字搜索" search enter-button :disabled="dtKeySearchSubmitting" @on-search="keySearchDt($event)" />
        </Col>
        <Col :xs="8" :sm="10" :md="14" :lg="18">
          <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="900" v-model="dtQueryPoptipShow">
            <Button icon="ios-locate-outline">精准查询</Button>
            <div slot="content">
              <Form class="pro-margin-top-10" ref="dtQueryForm" :model="dtQuery" :label-width="120">
                <Row>
                  <Col span="8">
                    <FormItem label="数据库表sql：" prop="dtSql">
                      <Input v-model="dtQuery.dtSql" placeholder="输入数据库表sql"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="表名：" prop="dtName">
                      <Input v-model="dtQuery.dtName" placeholder="输入表名"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="表名注释：" prop="dtNameAnnotation">
                      <Input v-model="dtQuery.dtNameAnnotation" placeholder="输入表名注释"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="数据库表前缀：" prop="dtPrefix">
                      <Input v-model="dtQuery.dtPrefix" placeholder="输入数据库表前缀"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="首字母大写实体类名：" prop="initialCaseEntityName">
                      <Input v-model="dtQuery.initialCaseEntityName" placeholder="输入首字母大写实体类名"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="首字母小写实体类名：" prop="initialLowercaseEntityName">
                      <Input v-model="dtQuery.initialLowercaseEntityName" placeholder="输入首字母小写实体类名"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="项目路径：" prop="proPath">
                      <Input v-model="dtQuery.proPath" placeholder="输入项目路径"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="项目全称：" prop="proAllName">
                      <Input v-model="dtQuery.proAllName" placeholder="输入项目全称"></Input>
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
      <Table :columns="dtColumns" :data="dtSet" @on-selection-change="setDtSelectRow($event)" @on-row-dblclick="dblclickDetailDt($event)"></Table>
      <Row class="pro-margin-top-10" type="flex" justify="center">
        <Page size="small" placement="top" show-total show-sizer show-elevator :total="dtCount" :current="dtPage" :page-size-opts="[10, 30, 50]" @on-change="dtPageIndexChange($event)" @on-page-size-change="dtPageSizeChange($event)" v-if="dtPageShow" />
        <Button type="text" shape="circle" icon="md-refresh" @click="dtRefresh()"></Button>
      </Row>
      <Spin size="large" fix v-if="dtSpinShow"></Spin>
    </Card>
    <Modal width="60" v-model="dtEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑数据库表
        </p>
        <Form class="pro-margin-top-20" ref="dtForm" :model="dt" :rules="dtFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="数据库表sql：" prop="dtSql">
                <Input v-model="dt.dtSql" placeholder="输入数据库表sql"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="表名：" prop="dtName">
                <Input v-model="dt.dtName" placeholder="输入表名"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="表名注释：" prop="dtNameAnnotation">
                <Input v-model="dt.dtNameAnnotation" placeholder="输入表名注释"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="数据库表前缀：" prop="dtPrefix">
                <Input v-model="dt.dtPrefix" placeholder="输入数据库表前缀"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="首字母大写实体类名：" prop="initialCaseEntityName">
                <Input v-model="dt.initialCaseEntityName" placeholder="输入首字母大写实体类名"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="首字母小写实体类名：" prop="initialLowercaseEntityName">
                <Input v-model="dt.initialLowercaseEntityName" placeholder="输入首字母小写实体类名"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="项目路径：" prop="proPath">
                <Input v-model="dt.proPath" placeholder="输入项目路径"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="项目全称：" prop="proAllName">
                <Input v-model="dt.proAllName" placeholder="输入项目全称"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
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
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据库表sql：{{ dt.dtSql }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>表名：{{ dt.dtName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>表名注释：{{ dt.dtNameAnnotation }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据库表前缀：{{ dt.dtPrefix }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>首字母大写实体类名：{{ dt.initialCaseEntityName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>首字母小写实体类名：{{ dt.initialLowercaseEntityName }}</p>
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
        </Row>
      </Card>
    </Drawer>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import Dt from '@/view/cn/moonnow/code/dt/Dt'
import DtQuery from '@/view/cn/moonnow/code/dt/DtQuery'

export default {
  data () {
    return {
      dtColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '数据库表sql', key: 'dtSql', minWidth: 200 },
        { title: '表名', key: 'dtName', minWidth: 200 },
        { title: '表名注释', key: 'dtNameAnnotation', minWidth: 200 },
        { title: '数据库表前缀', key: 'dtPrefix', minWidth: 200 },
        { title: '首字母大写实体类名', key: 'initialCaseEntityName', minWidth: 200 },
        { title: '首字母小写实体类名', key: 'initialLowercaseEntityName', minWidth: 200 },
        { title: '项目路径', key: 'proPath', minWidth: 200 },
        { title: '项目全称', key: 'proAllName', minWidth: 200 }
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
      dtKeySearchClickRate: 0
    }
  },
  computed: {
    ...mapState('dtStore', [
    ]),
    ...mapGetters('dtStore', [
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
      'queryDt',
      'pagingQueryDt'
    ]),
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
    submitDtForm () {
      this.dtSubmitting = true
      this.$refs.dtForm.validate((valid) => {
        if (valid) {
          if (this.dt.dtId !== null && this.dt.dtId !== '') {
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
        dtQueryKeySearch.dtSqlAndKeyLike = '%' + keyLikeValue + '%'
        dtQueryKeySearch.dtNameOrKeyLike = '%' + keyLikeValue + '%'
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
    }
  },
  mounted () {
    this.getDtSet()
  }
}
</script>
