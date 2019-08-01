<style lang="less" scoped>
  @import './accountGrpNexus.less';
</style>

<template>
  <div>
    <Card>
      <Row>
        <Col :xs="16" :sm="14" :md="10" :lg="6">
          <Input placeholder="关键字搜索" search enter-button :disabled="accountGrpNexusKeySearchSubmitting" @on-search="keySearchAccountGrpNexus($event)" />
        </Col>
        <Col :xs="8" :sm="10" :md="14" :lg="18">
          <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="900" v-model="accountGrpNexusQueryPoptipShow">
            <Button icon="ios-locate-outline">精准查询</Button>
            <div slot="content">
              <Form class="pro-margin-top-10" ref="accountGrpNexusQueryForm" :model="accountGrpNexusQuery" :label-width="120">
                <Row>
                  <Col span="8">
                    <FormItem label="账号编号：" prop="accountId">
                      <Input v-model="accountGrpNexusQuery.accountId" placeholder="输入账号编号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="组编号：" prop="grpId">
                      <Input v-model="accountGrpNexusQuery.grpId" placeholder="输入组编号"></Input>
                    </FormItem>
                  </Col>
                </Row>
                <Divider />
                <Row>
                  <Col span="24">
                    <FormItem class="pro-float-right">
                      <Button type="primary" @click="vagueQueryAccountGrpNexus()" :loading="accountGrpNexusQuerySubmitting">精准查询</Button>
                      <Button class="pro-margin-left-8" @click="resetAccountGrpNexusQueryForm()">重置</Button>
                    </FormItem>
                  </Col>
                </Row>
              </Form>
            </div>
          </Poptip>
        </Col>
      </Row>
      <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addAccountGrpNexus()">新建</Button>
      <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upAccountGrpNexus()" v-if="accountGrpNexusSelectRowLength === 1">编辑</Button>
      <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delAccountGrpNexus()" v-if="accountGrpNexusSelectRowLength > 0">
        <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
      </Poptip>
      <Button class="pro-list-button" icon="md-bulb" @click="detailAccountGrpNexus()" v-if="accountGrpNexusSelectRowLength === 1">详情</Button>
      <Table :columns="accountGrpNexusColumns" :data="accountGrpNexusSet" @on-selection-change="setAccountGrpNexusSelectRow($event)" @on-row-dblclick="dblclickDetailAccountGrpNexus($event)"></Table>
      <Row class="pro-margin-top-10" type="flex" justify="center">
        <Page size="small" placement="top" show-total show-sizer show-elevator :total="accountGrpNexusCount" :current="accountGrpNexusPage" :page-size-opts="[10, 30, 50]" @on-change="accountGrpNexusPageIndexChange($event)" @on-page-size-change="accountGrpNexusPageSizeChange($event)" v-if="accountGrpNexusPageShow" />
        <Button type="text" shape="circle" icon="md-refresh" @click="accountGrpNexusRefresh()"></Button>
      </Row>
      <Spin size="large" fix v-if="accountGrpNexusSpinShow"></Spin>
    </Card>
    <Modal width="60" v-model="accountGrpNexusEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑账号分组
        </p>
        <Form class="pro-margin-top-20" ref="accountGrpNexusForm" :model="accountGrpNexus" :rules="accountGrpNexusFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="账号编号：" prop="accountId">
                <Input v-model="accountGrpNexus.accountId" placeholder="输入账号编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="组编号：" prop="grpId">
                <Input v-model="accountGrpNexus.grpId" placeholder="输入组编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem>
                <Button class="pro-margin-right-8" @click="toAccountGrpNexusList()">取消</Button>
                <Button class="pro-margin-right-8" @click="resetAccountGrpNexusForm()">重置</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="accountGrpNexusSubmitting" @click="saveAccountGrpNexusForm()">保存</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="accountGrpNexusSubmitting" @click="saveAddAccountGrpNexusForm()">继续录入</Button>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    </Modal>
    <Drawer width="45" v-model="accountGrpNexusDetailDrawerShow" :closable="false">
      <Card>
        <p slot="title">
          <Icon type="md-bulb"></Icon>
          账号分组详情
        </p>
        <Row>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>账号分组编号：{{ accountGrpNexus.nexusId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>账号编号：{{ accountGrpNexus.accountId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>组编号：{{ accountGrpNexus.grpId }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import AccountGrpNexus from '@/view/cn/moonnow/sign/accountGrpNexus/AccountGrpNexus'
import AccountGrpNexusQuery from '@/view/cn/moonnow/sign/accountGrpNexus/AccountGrpNexusQuery'

export default {
  data () {
    return {
      accountGrpNexusColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '账号编号', key: 'accountId', minWidth: 200 },
        { title: '组编号', key: 'grpId', minWidth: 200 }
      ],
      accountGrpNexusFormValidate: {
        accountId: [
          { required: true, message: '账号编号不能为空', trigger: 'blur' }
        ],
        grpId: [
          { required: true, message: '组编号不能为空', trigger: 'blur' }
        ]
      },
      accountGrpNexusSet: [],
      accountGrpNexusCount: 0,
      accountGrpNexusRows: 10,
      accountGrpNexusPage: 1,
      accountGrpNexusSelectRow: [],
      accountGrpNexusSelectRowLength: 0,
      accountGrpNexus: new AccountGrpNexus(),
      accountGrpNexusQuery: new AccountGrpNexusQuery(),
      accountGrpNexusSpinShow: false,
      accountGrpNexusPageShow: true,
      accountGrpNexusEditModalShow: false,
      accountGrpNexusSubmitting: false,
      accountGrpNexusContinue: false,
      accountGrpNexusDetailDrawerShow: false,
      accountGrpNexusQueryPoptipShow: false,
      accountGrpNexusQuerySubmitting: false,
      accountGrpNexusKeySearchSubmitting: false,
      accountGrpNexusKeySearchClickRate: 0
    }
  },
  computed: {
    ...mapState('accountGrpNexusStore', [
    ]),
    ...mapGetters('accountGrpNexusStore', [
    ])
  },
  methods: {
    ...mapMutations('accountGrpNexusStore', [
    ]),
    ...mapActions('accountGrpNexusStore', [
      'saveAccountGrpNexus',
      'updateAccountGrpNexus',
      'batchRemoveAccountGrpNexus',
      'getAccountGrpNexusByPk',
      'queryAccountGrpNexus',
      'pagingQueryAccountGrpNexus'
    ]),
    addAccountGrpNexus () {
      this.accountGrpNexusEditModalShow = true
      this.accountGrpNexus = new AccountGrpNexus()
    },
    upAccountGrpNexus () {
      this.accountGrpNexusEditModalShow = true
      let accountGrpNexusEdit = new AccountGrpNexus()
      accountGrpNexusEdit.nexusId = this.accountGrpNexusSelectRow[0].nexusId
      this.getAccountGrpNexusByPk(accountGrpNexusEdit).then(res => {
        if (res.data.success) {
          Object.assign(this.accountGrpNexus, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toAccountGrpNexusList () {
      this.accountGrpNexusEditModalShow = false
      this.resetAccountGrpNexusForm()
      this.getAccountGrpNexusSet()
    },
    resetAccountGrpNexusForm () {
      this.$refs.accountGrpNexusForm.resetFields()
    },
    saveAccountGrpNexusForm () {
      this.accountGrpNexusContinue = false
      this.submitAccountGrpNexusForm()
    },
    saveAddAccountGrpNexusForm () {
      this.accountGrpNexusContinue = true
      this.submitAccountGrpNexusForm()
    },
    submitAccountGrpNexusForm () {
      this.accountGrpNexusSubmitting = true
      this.$refs.accountGrpNexusForm.validate((valid) => {
        if (valid) {
          if (this.accountGrpNexus.nexusId) {
            this.updateAccountGrpNexus(this.accountGrpNexus).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '账号分组信息修改成功.', duration: 3 })
                this.resetAccountGrpNexusForm()
                this.accountGrpNexus = new AccountGrpNexus()
                this.accountGrpNexusSubmitting = false
                if (!this.accountGrpNexusContinue) {
                  this.toAccountGrpNexusList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.accountGrpNexusSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.accountGrpNexusSubmitting = false }, 3000)
            })
          } else {
            this.saveAccountGrpNexus(this.accountGrpNexus).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '账号分组信息保存成功.', duration: 3 })
                this.resetAccountGrpNexusForm()
                this.accountGrpNexus = new AccountGrpNexus()
                this.accountGrpNexusSubmitting = false
                if (!this.accountGrpNexusContinue) {
                  this.toAccountGrpNexusList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.accountGrpNexusSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.accountGrpNexusSubmitting = false }, 3000)
            })
          }
        } else {
          setTimeout(() => { this.accountGrpNexusSubmitting = false }, 3000)
        }
      })
    },
    delAccountGrpNexus () {
      this.accountGrpNexusSpinShow = true
      this.batchRemoveAccountGrpNexus(this.accountGrpNexusSelectRow).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '账号分组信息删除成功.', duration: 3 })
          this.setAccountGrpNexusSelectRow([])
          this.getAccountGrpNexusSet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.accountGrpNexusSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.accountGrpNexusSpinShow = false }, 700)
      })
    },
    detailAccountGrpNexus () {
      this.accountGrpNexusDetailDrawerShow = true
      let accountGrpNexusDetail = new AccountGrpNexus()
      accountGrpNexusDetail.nexusId = this.accountGrpNexusSelectRow[0].nexusId
      this.getAccountGrpNexusByPk(accountGrpNexusDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.accountGrpNexus, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    dblclickDetailAccountGrpNexus (rowData) {
      this.accountGrpNexusDetailDrawerShow = true
      let accountGrpNexusDetail = new AccountGrpNexus()
      accountGrpNexusDetail.nexusId = rowData.nexusId
      this.getAccountGrpNexusByPk(accountGrpNexusDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.accountGrpNexus, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getAccountGrpNexusSet () {
      this.accountGrpNexusSpinShow = true
      this.accountGrpNexusPageShow = true
      this.pagingQueryAccountGrpNexus({ accountGrpNexusQuery: {}, rows: this.accountGrpNexusRows, page: this.accountGrpNexusPage }).then(res => {
        if (res.data.success) {
          this.accountGrpNexusSet = res.data.data
          this.accountGrpNexusCount = res.data.count
          this.setAccountGrpNexusSelectRow([])
          setTimeout(() => { this.accountGrpNexusSpinShow = false }, 700)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.accountGrpNexusSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.accountGrpNexusSpinShow = false }, 700)
      })
    },
    keySearchAccountGrpNexus (keyLikeValue) {
      if (keyLikeValue !== '') {
        this.accountGrpNexusKeySearchSubmitting = true
        this.accountGrpNexusPageShow = false
        this.accountGrpNexusSpinShow = true
        let accountGrpNexusQueryKeySearch = new AccountGrpNexusQuery()
        accountGrpNexusQueryKeySearch.accountIdAndKeyLike = '%' + keyLikeValue + '%'
        accountGrpNexusQueryKeySearch.grpIdOrKeyLike = '%' + keyLikeValue + '%'
        this.queryAccountGrpNexus(accountGrpNexusQueryKeySearch).then(res => {
          if (res.data.success) {
            this.accountGrpNexusSet = res.data.data
            this.accountGrpNexusCount = res.data.count
            this.setAccountGrpNexusSelectRow([])
            setTimeout(() => { this.accountGrpNexusSpinShow = false }, 700)
            setTimeout(() => { this.accountGrpNexusKeySearchSubmitting = false }, 5000)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.accountGrpNexusSpinShow = false }, 700)
            setTimeout(() => { this.accountGrpNexusKeySearchSubmitting = false }, 5000)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.accountGrpNexusSpinShow = false }, 700)
          setTimeout(() => { this.accountGrpNexusKeySearchSubmitting = false }, 5000)
        })
      } else {
        this.accountGrpNexusKeySearchClickRate++
        if (this.accountGrpNexusKeySearchClickRate > 10) {
          this.$Message.warning({ content: '兄dei，你是闲的吗？', duration: 6 })
          this.accountGrpNexusKeySearchClickRate = 0
        }
      }
    },
    vagueQueryAccountGrpNexus () {
      this.accountGrpNexusQuerySubmitting = true
      this.accountGrpNexusPageShow = false
      this.accountGrpNexusSpinShow = true
      this.accountGrpNexusQueryPoptipShow = false
      this.queryAccountGrpNexus(this.accountGrpNexusQuery).then(res => {
        if (res.data.success) {
          this.accountGrpNexusSet = res.data.data
          this.accountGrpNexusCount = res.data.count
          this.setAccountGrpNexusSelectRow([])
          setTimeout(() => { this.accountGrpNexusSpinShow = false }, 700)
          setTimeout(() => { this.accountGrpNexusQuerySubmitting = false }, 5000)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.accountGrpNexusSpinShow = false }, 700)
          setTimeout(() => { this.accountGrpNexusQuerySubmitting = false }, 5000)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.accountGrpNexusSpinShow = false }, 700)
        setTimeout(() => { this.accountGrpNexusQuerySubmitting = false }, 5000)
      })
    },
    resetAccountGrpNexusQueryForm () {
      this.$refs.accountGrpNexusQueryForm.resetFields()
    },
    setAccountGrpNexusSelectRow (list) {
      this.accountGrpNexusSelectRow = list
      this.accountGrpNexusSelectRowLength = list.length
    },
    accountGrpNexusPageIndexChange (pageIndex) {
      this.accountGrpNexusPage = pageIndex
      this.getAccountGrpNexusSet()
    },
    accountGrpNexusPageSizeChange (pageSize) {
      this.accountGrpNexusRows = pageSize
      this.accountGrpNexusPage = 1
      this.getAccountGrpNexusSet()
    },
    accountGrpNexusRefresh () {
      this.accountGrpNexusPage = 1
      this.getAccountGrpNexusSet()
    }
  },
  mounted () {
    this.getAccountGrpNexusSet()
  }
}
</script>
