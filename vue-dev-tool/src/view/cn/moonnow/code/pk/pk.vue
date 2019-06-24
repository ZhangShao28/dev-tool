<style lang="less" scoped>
  @import './pk.less';
</style>

<template>
  <div>
    <Card>
      <Row>
        <Col :xs="16" :sm="14" :md="10" :lg="6">
          <Input placeholder="关键字搜索" search enter-button :disabled="pkKeySearchSubmitting" @on-search="keySearchPk($event)" />
        </Col>
        <Col :xs="8" :sm="10" :md="14" :lg="18">
          <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="900" v-model="pkQueryPoptipShow">
            <Button icon="ios-locate-outline">精准查询</Button>
            <div slot="content">
              <Form class="pro-margin-top-10" ref="pkQueryForm" :model="pkQuery" :label-width="120">
                <Row>
                  <Col span="8">
                    <FormItem label="数据库表编号：" prop="dtId">
                      <Input v-model="pkQuery.dtId" placeholder="输入数据库表编号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="列编号：" prop="columnsId">
                      <Input v-model="pkQuery.columnsId" placeholder="输入列编号"></Input>
                    </FormItem>
                  </Col>
                </Row>
                <Divider />
                <Row>
                  <Col span="24">
                    <FormItem class="pro-float-right">
                      <Button type="primary" @click="vagueQueryPk()" :loading="pkQuerySubmitting">精准查询</Button>
                      <Button class="pro-margin-left-8" @click="resetPkQueryForm()">重置</Button>
                    </FormItem>
                  </Col>
                </Row>
              </Form>
            </div>
          </Poptip>
        </Col>
      </Row>
      <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addPk()">新建</Button>
      <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upPk()" v-if="pkSelectRowLength === 1">编辑</Button>
      <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delPk()" v-if="pkSelectRowLength > 0">
        <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
      </Poptip>
      <Button class="pro-list-button" icon="md-bulb" @click="detailPk()" v-if="pkSelectRowLength === 1">详情</Button>
      <Table :columns="pkColumns" :data="pkSet" @on-selection-change="setPkSelectRow($event)" @on-row-dblclick="dblclickDetailPk($event)"></Table>
      <Row class="pro-margin-top-10" type="flex" justify="center">
        <Page size="small" placement="top" show-total show-sizer show-elevator :total="pkCount" :current="pkPage" :page-size-opts="[10, 30, 50]" @on-change="pkPageIndexChange($event)" @on-page-size-change="pkPageSizeChange($event)" v-if="pkPageShow" />
        <Button type="text" shape="circle" icon="md-refresh" @click="pkRefresh()"></Button>
      </Row>
      <Spin size="large" fix v-if="pkSpinShow"></Spin>
    </Card>
    <Modal width="60" v-model="pkEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑主键
        </p>
        <Form class="pro-margin-top-20" ref="pkForm" :model="pk" :rules="pkFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="数据库表编号：" prop="dtId">
                <Input v-model="pk.dtId" placeholder="输入数据库表编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="列编号：" prop="columnsId">
                <Input v-model="pk.columnsId" placeholder="输入列编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem>
                <Button class="pro-margin-right-8" @click="toPkList()">取消</Button>
                <Button class="pro-margin-right-8" @click="resetPkForm()">重置</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="pkSubmitting" @click="savePkForm()">保存</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="pkSubmitting" @click="saveAddPkForm()">继续录入</Button>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    </Modal>
    <Drawer width="45" v-model="pkDetailDrawerShow" :closable="false">
      <Card>
        <p slot="title">
          <Icon type="md-bulb"></Icon>
          主键详情
        </p>
        <Row>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>主键编号：{{ pk.pkId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据库表编号：{{ pk.dtId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>列编号：{{ pk.columnsId }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import Pk from '@/view/cn/moonnow/code/pk/Pk'
import PkQuery from '@/view/cn/moonnow/code/pk/PkQuery'

export default {
  data () {
    return {
      pkColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '数据库表编号', key: 'dtId', minWidth: 200 },
        { title: '列编号', key: 'columnsId', minWidth: 200 }
      ],
      pkFormValidate: {
        dtId: [
          { required: true, message: '数据库表编号不能为空', trigger: 'blur' }
        ],
        columnsId: [
          { required: true, message: '列编号不能为空', trigger: 'blur' }
        ]
      },
      pkSet: [],
      pkCount: 0,
      pkRows: 10,
      pkPage: 1,
      pkSelectRow: [],
      pkSelectRowLength: 0,
      pk: new Pk(),
      pkQuery: new PkQuery(),
      pkSpinShow: false,
      pkPageShow: true,
      pkEditModalShow: false,
      pkSubmitting: false,
      pkContinue: false,
      pkDetailDrawerShow: false,
      pkQueryPoptipShow: false,
      pkQuerySubmitting: false,
      pkKeySearchSubmitting: false,
      pkKeySearchClickRate: 0
    }
  },
  computed: {
    ...mapState('pkStore', [
    ]),
    ...mapGetters('pkStore', [
    ])
  },
  methods: {
    ...mapMutations('pkStore', [
    ]),
    ...mapActions('pkStore', [
      'savePk',
      'updatePk',
      'batchRemovePk',
      'getPkByPk',
      'queryPk',
      'pagingQueryPk'
    ]),
    addPk () {
      this.pkEditModalShow = true
      this.pk = new Pk()
    },
    upPk () {
      this.pkEditModalShow = true
      let pkEdit = new Pk()
      pkEdit.pkId = this.pkSelectRow[0].pkId
      this.getPkByPk(pkEdit).then(res => {
        if (res.data.success) {
          Object.assign(this.pk, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toPkList () {
      this.pkEditModalShow = false
      this.resetPkForm()
      this.getPkSet()
    },
    resetPkForm () {
      this.$refs.pkForm.resetFields()
    },
    savePkForm () {
      this.pkContinue = false
      this.submitPkForm()
    },
    saveAddPkForm () {
      this.pkContinue = true
      this.submitPkForm()
    },
    submitPkForm () {
      this.pkSubmitting = true
      this.$refs.pkForm.validate((valid) => {
        if (valid) {
          if (this.pk.pkId) {
            this.updatePk(this.pk).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '主键信息修改成功.', duration: 3 })
                this.resetPkForm()
                this.pk = new Pk()
                this.pkSubmitting = false
                if (!this.pkContinue) {
                  this.toPkList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.pkSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.pkSubmitting = false }, 3000)
            })
          } else {
            this.savePk(this.pk).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '主键信息保存成功.', duration: 3 })
                this.resetPkForm()
                this.pk = new Pk()
                this.pkSubmitting = false
                if (!this.pkContinue) {
                  this.toPkList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.pkSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.pkSubmitting = false }, 3000)
            })
          }
        } else {
          setTimeout(() => { this.pkSubmitting = false }, 3000)
        }
      })
    },
    delPk () {
      this.pkSpinShow = true
      this.batchRemovePk(this.pkSelectRow).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '主键信息删除成功.', duration: 3 })
          this.setPkSelectRow([])
          this.getPkSet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.pkSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.pkSpinShow = false }, 700)
      })
    },
    detailPk () {
      this.pkDetailDrawerShow = true
      let pkDetail = new Pk()
      pkDetail.pkId = this.pkSelectRow[0].pkId
      this.getPkByPk(pkDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.pk, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    dblclickDetailPk (rowData) {
      this.pkDetailDrawerShow = true
      let pkDetail = new Pk()
      pkDetail.pkId = rowData.pkId
      this.getPkByPk(pkDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.pk, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getPkSet () {
      this.pkSpinShow = true
      this.pkPageShow = true
      this.pagingQueryPk({ pkQuery: {}, rows: this.pkRows, page: this.pkPage }).then(res => {
        if (res.data.success) {
          this.pkSet = res.data.data
          this.pkCount = res.data.count
          this.setPkSelectRow([])
          setTimeout(() => { this.pkSpinShow = false }, 700)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.pkSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.pkSpinShow = false }, 700)
      })
    },
    keySearchPk (keyLikeValue) {
      if (keyLikeValue !== '') {
        this.pkKeySearchSubmitting = true
        this.pkPageShow = false
        this.pkSpinShow = true
        let pkQueryKeySearch = new PkQuery()
        pkQueryKeySearch.dtIdAndKeyLike = '%' + keyLikeValue + '%'
        pkQueryKeySearch.columnsIdOrKeyLike = '%' + keyLikeValue + '%'
        this.queryPk(pkQueryKeySearch).then(res => {
          if (res.data.success) {
            this.pkSet = res.data.data
            this.pkCount = res.data.count
            this.setPkSelectRow([])
            setTimeout(() => { this.pkSpinShow = false }, 700)
            setTimeout(() => { this.pkKeySearchSubmitting = false }, 5000)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.pkSpinShow = false }, 700)
            setTimeout(() => { this.pkKeySearchSubmitting = false }, 5000)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.pkSpinShow = false }, 700)
          setTimeout(() => { this.pkKeySearchSubmitting = false }, 5000)
        })
      } else {
        this.pkKeySearchClickRate++
        if (this.pkKeySearchClickRate > 10) {
          this.$Message.warning({ content: '兄dei，你是闲的吗？', duration: 6 })
          this.pkKeySearchClickRate = 0
        }
      }
    },
    vagueQueryPk () {
      this.pkQuerySubmitting = true
      this.pkPageShow = false
      this.pkSpinShow = true
      this.pkQueryPoptipShow = false
      this.queryPk(this.pkQuery).then(res => {
        if (res.data.success) {
          this.pkSet = res.data.data
          this.pkCount = res.data.count
          this.setPkSelectRow([])
          setTimeout(() => { this.pkSpinShow = false }, 700)
          setTimeout(() => { this.pkQuerySubmitting = false }, 5000)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.pkSpinShow = false }, 700)
          setTimeout(() => { this.pkQuerySubmitting = false }, 5000)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.pkSpinShow = false }, 700)
        setTimeout(() => { this.pkQuerySubmitting = false }, 5000)
      })
    },
    resetPkQueryForm () {
      this.$refs.pkQueryForm.resetFields()
    },
    setPkSelectRow (list) {
      this.pkSelectRow = list
      this.pkSelectRowLength = list.length
    },
    pkPageIndexChange (pageIndex) {
      this.pkPage = pageIndex
      this.getPkSet()
    },
    pkPageSizeChange (pageSize) {
      this.pkRows = pageSize
      this.pkPage = 1
      this.getPkSet()
    },
    pkRefresh () {
      this.pkPage = 1
      this.getPkSet()
    }
  },
  mounted () {
    this.getPkSet()
  }
}
</script>
