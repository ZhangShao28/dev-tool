<style lang="less" scoped>
  @import './account.less';
</style>

<template>
  <div>
    <Card>
      <Row>
        <Col :xs="16" :sm="14" :md="10" :lg="6">
          <Input placeholder="关键字搜索" search enter-button :disabled="accountKeySearchSubmitting" @on-search="keySearchAccount($event)" />
        </Col>
        <Col :xs="8" :sm="10" :md="14" :lg="18">
          <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="900" v-model="accountQueryPoptipShow">
            <Button icon="ios-locate-outline">精准查询</Button>
            <div slot="content">
              <Form class="pro-margin-top-10" ref="accountQueryForm" :model="accountQuery" :label-width="120">
                <Row>
                  <Col span="8">
                    <FormItem label="账号：" prop="account">
                      <Input v-model="accountQuery.account" placeholder="输入账号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="密码：" prop="password">
                      <Input v-model="accountQuery.password" placeholder="输入密码"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="手机号：" prop="mobilePhoneNumber">
                      <Input v-model="accountQuery.mobilePhoneNumber" placeholder="输入手机号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="关联类型：" prop="nexusType">
                      <Input v-model="accountQuery.nexusType" placeholder="输入关联类型"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="对象编号：" prop="nexusObjectId">
                      <Input v-model="accountQuery.nexusObjectId" placeholder="输入对象编号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="版本号：" prop="versions">
                      <InputNumber v-model="accountQuery.versions"></InputNumber>
                    </FormItem>
                  </Col>
                </Row>
                <Divider />
                <Row>
                  <Col span="24">
                    <FormItem class="pro-float-right">
                      <Button type="primary" @click="vagueQueryAccount()" :loading="accountQuerySubmitting">精准查询</Button>
                      <Button class="pro-margin-left-8" @click="resetAccountQueryForm()">重置</Button>
                    </FormItem>
                  </Col>
                </Row>
              </Form>
            </div>
          </Poptip>
        </Col>
      </Row>
      <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addAccount()">新建</Button>
      <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upAccount()" v-if="accountSelectRowLength === 1">编辑</Button>
      <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delAccount()" v-if="accountSelectRowLength > 0">
        <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
      </Poptip>
      <Button class="pro-list-button" icon="md-bulb" @click="detailAccount()" v-if="accountSelectRowLength === 1">详情</Button>
      <Table :columns="accountColumns" :data="accountSet" @on-selection-change="setAccountSelectRow($event)" @on-row-dblclick="dblclickDetailAccount($event)"></Table>
      <Row class="pro-margin-top-10" type="flex" justify="center">
        <Page size="small" placement="top" show-total show-sizer show-elevator :total="accountCount" :current="accountPage" :page-size-opts="[10, 30, 50]" @on-change="accountPageIndexChange($event)" @on-page-size-change="accountPageSizeChange($event)" v-if="accountPageShow" />
        <Button type="text" shape="circle" icon="md-refresh" @click="accountRefresh()"></Button>
      </Row>
      <Spin size="large" fix v-if="accountSpinShow"></Spin>
    </Card>
    <Modal width="60" v-model="accountEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑账号
        </p>
        <Form class="pro-margin-top-20" ref="accountForm" :model="account" :rules="accountFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="账号：" prop="account">
                <Input v-model="account.account" placeholder="输入账号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="密码：" prop="password">
                <Input v-model="account.password" placeholder="输入密码"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="手机号：" prop="mobilePhoneNumber">
                <Input v-model="account.mobilePhoneNumber" placeholder="输入手机号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="关联类型：" prop="nexusType">
                <Input v-model="account.nexusType" placeholder="输入关联类型"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="对象编号：" prop="nexusObjectId">
                <Input v-model="account.nexusObjectId" placeholder="输入对象编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="版本号：" prop="versions">
                <InputNumber v-model="account.versions"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem>
                <Button class="pro-margin-right-8" @click="toAccountList()">取消</Button>
                <Button class="pro-margin-right-8" @click="resetAccountForm()">重置</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="accountSubmitting" @click="saveAccountForm()">保存</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="accountSubmitting" @click="saveAddAccountForm()">继续录入</Button>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    </Modal>
    <Drawer width="45" v-model="accountDetailDrawerShow" :closable="false">
      <Card>
        <p slot="title">
          <Icon type="md-bulb"></Icon>
          账号详情
        </p>
        <Row>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>账号编号：{{ account.accountId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>账号：{{ account.account }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>密码：{{ account.password }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>手机号：{{ account.mobilePhoneNumber }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>关联类型：{{ account.nexusType }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>对象编号：{{ account.nexusObjectId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>版本号：{{ account.versions }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import Account from '@/view/cn/moonnow/sign/account/Account'
import AccountQuery from '@/view/cn/moonnow/sign/account/AccountQuery'

export default {
  data () {
    return {
      accountColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '账号', key: 'account', minWidth: 200 },
        { title: '密码', key: 'password', minWidth: 200 },
        { title: '手机号', key: 'mobilePhoneNumber', minWidth: 200 },
        { title: '关联类型', key: 'nexusType', minWidth: 200 },
        { title: '对象编号', key: 'nexusObjectId', minWidth: 200 },
        { title: '版本号', key: 'versions', minWidth: 200 }
      ],
      accountFormValidate: {
        account: [
          { required: true, message: '账号不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
        versions: [
          { type: 'number', required: true, message: '版本号不能为空', trigger: 'change' }
        ]
      },
      accountSet: [],
      accountCount: 0,
      accountRows: 10,
      accountPage: 1,
      accountSelectRow: [],
      accountSelectRowLength: 0,
      account: new Account(),
      accountQuery: new AccountQuery(),
      accountSpinShow: false,
      accountPageShow: true,
      accountEditModalShow: false,
      accountSubmitting: false,
      accountContinue: false,
      accountDetailDrawerShow: false,
      accountQueryPoptipShow: false,
      accountQuerySubmitting: false,
      accountKeySearchSubmitting: false,
      accountKeySearchClickRate: 0
    }
  },
  computed: {
    ...mapState('accountStore', [
    ]),
    ...mapGetters('accountStore', [
    ])
  },
  methods: {
    ...mapMutations('accountStore', [
    ]),
    ...mapActions('accountStore', [
      'saveAccount',
      'updateAccount',
      'batchRemoveAccount',
      'getAccountByPk',
      'queryAccount',
      'pagingQueryAccount'
    ]),
    addAccount () {
      this.accountEditModalShow = true
      this.account = new Account()
    },
    upAccount () {
      this.accountEditModalShow = true
      let accountEdit = new Account()
      accountEdit.accountId = this.accountSelectRow[0].accountId
      this.getAccountByPk(accountEdit).then(res => {
        if (res.data.success) {
          Object.assign(this.account, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toAccountList () {
      this.accountEditModalShow = false
      this.resetAccountForm()
      this.getAccountSet()
    },
    resetAccountForm () {
      this.$refs.accountForm.resetFields()
    },
    saveAccountForm () {
      this.accountContinue = false
      this.submitAccountForm()
    },
    saveAddAccountForm () {
      this.accountContinue = true
      this.submitAccountForm()
    },
    submitAccountForm () {
      this.accountSubmitting = true
      this.$refs.accountForm.validate((valid) => {
        if (valid) {
          if (this.account.accountId) {
            this.updateAccount(this.account).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '账号信息修改成功.', duration: 3 })
                this.resetAccountForm()
                this.account = new Account()
                this.accountSubmitting = false
                if (!this.accountContinue) {
                  this.toAccountList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.accountSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.accountSubmitting = false }, 3000)
            })
          } else {
            this.saveAccount(this.account).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '账号信息保存成功.', duration: 3 })
                this.resetAccountForm()
                this.account = new Account()
                this.accountSubmitting = false
                if (!this.accountContinue) {
                  this.toAccountList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.accountSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.accountSubmitting = false }, 3000)
            })
          }
        } else {
          setTimeout(() => { this.accountSubmitting = false }, 3000)
        }
      })
    },
    delAccount () {
      this.accountSpinShow = true
      this.batchRemoveAccount(this.accountSelectRow).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '账号信息删除成功.', duration: 3 })
          this.setAccountSelectRow([])
          this.getAccountSet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.accountSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.accountSpinShow = false }, 700)
      })
    },
    detailAccount () {
      this.accountDetailDrawerShow = true
      let accountDetail = new Account()
      accountDetail.accountId = this.accountSelectRow[0].accountId
      this.getAccountByPk(accountDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.account, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    dblclickDetailAccount (rowData) {
      this.accountDetailDrawerShow = true
      let accountDetail = new Account()
      accountDetail.accountId = rowData.accountId
      this.getAccountByPk(accountDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.account, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getAccountSet () {
      this.accountSpinShow = true
      this.accountPageShow = true
      this.pagingQueryAccount({ accountQuery: {}, rows: this.accountRows, page: this.accountPage }).then(res => {
        if (res.data.success) {
          this.accountSet = res.data.data
          this.accountCount = res.data.count
          this.setAccountSelectRow([])
          setTimeout(() => { this.accountSpinShow = false }, 700)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.accountSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.accountSpinShow = false }, 700)
      })
    },
    keySearchAccount (keyLikeValue) {
      if (keyLikeValue !== '') {
        this.accountKeySearchSubmitting = true
        this.accountPageShow = false
        this.accountSpinShow = true
        let accountQueryKeySearch = new AccountQuery()
        accountQueryKeySearch.accountAndKeyLike = '%' + keyLikeValue + '%'
        accountQueryKeySearch.passwordOrKeyLike = '%' + keyLikeValue + '%'
        accountQueryKeySearch.mobilePhoneNumberOrKeyLike = '%' + keyLikeValue + '%'
        accountQueryKeySearch.nexusTypeOrKeyLike = '%' + keyLikeValue + '%'
        accountQueryKeySearch.nexusObjectIdOrKeyLike = '%' + keyLikeValue + '%'
        this.queryAccount(accountQueryKeySearch).then(res => {
          if (res.data.success) {
            this.accountSet = res.data.data
            this.accountCount = res.data.count
            this.setAccountSelectRow([])
            setTimeout(() => { this.accountSpinShow = false }, 700)
            setTimeout(() => { this.accountKeySearchSubmitting = false }, 5000)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.accountSpinShow = false }, 700)
            setTimeout(() => { this.accountKeySearchSubmitting = false }, 5000)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.accountSpinShow = false }, 700)
          setTimeout(() => { this.accountKeySearchSubmitting = false }, 5000)
        })
      } else {
        this.accountKeySearchClickRate++
        if (this.accountKeySearchClickRate > 10) {
          this.$Message.warning({ content: '兄dei，你是闲的吗？', duration: 6 })
          this.accountKeySearchClickRate = 0
        }
      }
    },
    vagueQueryAccount () {
      this.accountQuerySubmitting = true
      this.accountPageShow = false
      this.accountSpinShow = true
      this.accountQueryPoptipShow = false
      this.queryAccount(this.accountQuery).then(res => {
        if (res.data.success) {
          this.accountSet = res.data.data
          this.accountCount = res.data.count
          this.setAccountSelectRow([])
          setTimeout(() => { this.accountSpinShow = false }, 700)
          setTimeout(() => { this.accountQuerySubmitting = false }, 5000)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.accountSpinShow = false }, 700)
          setTimeout(() => { this.accountQuerySubmitting = false }, 5000)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.accountSpinShow = false }, 700)
        setTimeout(() => { this.accountQuerySubmitting = false }, 5000)
      })
    },
    resetAccountQueryForm () {
      this.$refs.accountQueryForm.resetFields()
    },
    setAccountSelectRow (list) {
      this.accountSelectRow = list
      this.accountSelectRowLength = list.length
    },
    accountPageIndexChange (pageIndex) {
      this.accountPage = pageIndex
      this.getAccountSet()
    },
    accountPageSizeChange (pageSize) {
      this.accountRows = pageSize
      this.accountPage = 1
      this.getAccountSet()
    },
    accountRefresh () {
      this.accountPage = 1
      this.getAccountSet()
    }
  },
  mounted () {
    this.getAccountSet()
  }
}
</script>
