<style lang="less" scoped>
  @import './loginLog.less';
</style>

<template>
  <div>
    <Card>
      <Row>
        <Col :xs="16" :sm="14" :md="10" :lg="6">
          <Input placeholder="关键字搜索" search enter-button :disabled="loginLogKeySearchSubmitting" @on-search="keySearchLoginLog($event)" />
        </Col>
        <Col :xs="8" :sm="10" :md="14" :lg="18">
          <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="900" v-model="loginLogQueryPoptipShow">
            <Button icon="ios-locate-outline">精准查询</Button>
            <div slot="content">
              <Form class="pro-margin-top-10" ref="loginLogQueryForm" :model="loginLogQuery" :label-width="120">
                <Row>
                  <Col span="8">
                    <FormItem label="登录标识：" prop="loginKey">
                      <Input v-model="loginLogQuery.loginKey" placeholder="输入登录标识"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="账号编号：" prop="accountId">
                      <Input v-model="loginLogQuery.accountId" placeholder="输入账号编号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="账号：" prop="account">
                      <Input v-model="loginLogQuery.account" placeholder="输入账号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="手机号：" prop="mobilePhoneNumber">
                      <Input v-model="loginLogQuery.mobilePhoneNumber" placeholder="输入手机号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="登录时间：" prop="loginTimes">
                      <InputNumber v-model="loginLogQuery.loginTimes"></InputNumber>
                    </FormItem>
                  </Col>
                </Row>
                <Divider />
                <Row>
                  <Col span="24">
                    <FormItem class="pro-float-right">
                      <Button type="primary" @click="vagueQueryLoginLog()" :loading="loginLogQuerySubmitting">精准查询</Button>
                      <Button class="pro-margin-left-8" @click="resetLoginLogQueryForm()">重置</Button>
                    </FormItem>
                  </Col>
                </Row>
              </Form>
            </div>
          </Poptip>
        </Col>
      </Row>
      <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addLoginLog()">新建</Button>
      <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upLoginLog()" v-if="loginLogSelectRowLength === 1">编辑</Button>
      <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delLoginLog()" v-if="loginLogSelectRowLength > 0">
        <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
      </Poptip>
      <Button class="pro-list-button" icon="md-bulb" @click="detailLoginLog()" v-if="loginLogSelectRowLength === 1">详情</Button>
      <Table :columns="loginLogColumns" :data="loginLogSet" @on-selection-change="setLoginLogSelectRow($event)" @on-row-dblclick="dblclickDetailLoginLog($event)"></Table>
      <Row class="pro-margin-top-10" type="flex" justify="center">
        <Page size="small" placement="top" show-total show-sizer show-elevator :total="loginLogCount" :current="loginLogPage" :page-size-opts="[10, 30, 50]" @on-change="loginLogPageIndexChange($event)" @on-page-size-change="loginLogPageSizeChange($event)" v-if="loginLogPageShow" />
        <Button type="text" shape="circle" icon="md-refresh" @click="loginLogRefresh()"></Button>
      </Row>
      <Spin size="large" fix v-if="loginLogSpinShow"></Spin>
    </Card>
    <Modal width="60" v-model="loginLogEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑登录日志
        </p>
        <Form class="pro-margin-top-20" ref="loginLogForm" :model="loginLog" :rules="loginLogFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="登录标识：" prop="loginKey">
                <Input v-model="loginLog.loginKey" placeholder="输入登录标识"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="账号编号：" prop="accountId">
                <Input v-model="loginLog.accountId" placeholder="输入账号编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="账号：" prop="account">
                <Input v-model="loginLog.account" placeholder="输入账号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="手机号：" prop="mobilePhoneNumber">
                <Input v-model="loginLog.mobilePhoneNumber" placeholder="输入手机号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="登录时间：" prop="loginTimes">
                <InputNumber v-model="loginLog.loginTimes"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem>
                <Button class="pro-margin-right-8" @click="toLoginLogList()">取消</Button>
                <Button class="pro-margin-right-8" @click="resetLoginLogForm()">重置</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="loginLogSubmitting" @click="saveLoginLogForm()">保存</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="loginLogSubmitting" @click="saveAddLoginLogForm()">继续录入</Button>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    </Modal>
    <Drawer width="45" v-model="loginLogDetailDrawerShow" :closable="false">
      <Card>
        <p slot="title">
          <Icon type="md-bulb"></Icon>
          登录日志详情
        </p>
        <Row>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>登录日志编号：{{ loginLog.logId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>登录标识：{{ loginLog.loginKey }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>账号编号：{{ loginLog.accountId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>账号：{{ loginLog.account }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>手机号：{{ loginLog.mobilePhoneNumber }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>登录时间：{{ loginLog.loginTimes }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import LoginLog from '@/view/cn/moonnow/sign/loginLog/LoginLog'
import LoginLogQuery from '@/view/cn/moonnow/sign/loginLog/LoginLogQuery'

export default {
  data () {
    return {
      loginLogColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '登录标识', key: 'loginKey', minWidth: 200 },
        { title: '账号编号', key: 'accountId', minWidth: 200 },
        { title: '账号', key: 'account', minWidth: 200 },
        { title: '手机号', key: 'mobilePhoneNumber', minWidth: 200 },
        { title: '登录时间', key: 'loginTimes', minWidth: 200 }
      ],
      loginLogFormValidate: {
        loginKey: [
          { required: true, message: '登录标识不能为空', trigger: 'blur' }
        ],
        accountId: [
          { required: true, message: '账号编号不能为空', trigger: 'blur' }
        ],
        account: [
          { required: true, message: '账号不能为空', trigger: 'blur' }
        ],
        loginTimes: [
          { type: 'number', required: true, message: '登录时间不能为空', trigger: 'change' }
        ]
      },
      loginLogSet: [],
      loginLogCount: 0,
      loginLogRows: 10,
      loginLogPage: 1,
      loginLogSelectRow: [],
      loginLogSelectRowLength: 0,
      loginLog: new LoginLog(),
      loginLogQuery: new LoginLogQuery(),
      loginLogSpinShow: false,
      loginLogPageShow: true,
      loginLogEditModalShow: false,
      loginLogSubmitting: false,
      loginLogContinue: false,
      loginLogDetailDrawerShow: false,
      loginLogQueryPoptipShow: false,
      loginLogQuerySubmitting: false,
      loginLogKeySearchSubmitting: false,
      loginLogKeySearchClickRate: 0
    }
  },
  computed: {
    ...mapState('loginLogStore', [
    ]),
    ...mapGetters('loginLogStore', [
    ])
  },
  methods: {
    ...mapMutations('loginLogStore', [
    ]),
    ...mapActions('loginLogStore', [
      'saveLoginLog',
      'updateLoginLog',
      'batchRemoveLoginLog',
      'getLoginLogByPk',
      'queryLoginLog',
      'pagingQueryLoginLog'
    ]),
    addLoginLog () {
      this.loginLogEditModalShow = true
      this.loginLog = new LoginLog()
    },
    upLoginLog () {
      this.loginLogEditModalShow = true
      let loginLogEdit = new LoginLog()
      loginLogEdit.logId = this.loginLogSelectRow[0].logId
      this.getLoginLogByPk(loginLogEdit).then(res => {
        if (res.data.success) {
          Object.assign(this.loginLog, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toLoginLogList () {
      this.loginLogEditModalShow = false
      this.resetLoginLogForm()
      this.getLoginLogSet()
    },
    resetLoginLogForm () {
      this.$refs.loginLogForm.resetFields()
    },
    saveLoginLogForm () {
      this.loginLogContinue = false
      this.submitLoginLogForm()
    },
    saveAddLoginLogForm () {
      this.loginLogContinue = true
      this.submitLoginLogForm()
    },
    submitLoginLogForm () {
      this.loginLogSubmitting = true
      this.$refs.loginLogForm.validate((valid) => {
        if (valid) {
          if (this.loginLog.logId) {
            this.updateLoginLog(this.loginLog).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '登录日志信息修改成功.', duration: 3 })
                this.resetLoginLogForm()
                this.loginLog = new LoginLog()
                this.loginLogSubmitting = false
                if (!this.loginLogContinue) {
                  this.toLoginLogList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.loginLogSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.loginLogSubmitting = false }, 3000)
            })
          } else {
            this.saveLoginLog(this.loginLog).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '登录日志信息保存成功.', duration: 3 })
                this.resetLoginLogForm()
                this.loginLog = new LoginLog()
                this.loginLogSubmitting = false
                if (!this.loginLogContinue) {
                  this.toLoginLogList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.loginLogSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.loginLogSubmitting = false }, 3000)
            })
          }
        } else {
          setTimeout(() => { this.loginLogSubmitting = false }, 3000)
        }
      })
    },
    delLoginLog () {
      this.loginLogSpinShow = true
      this.batchRemoveLoginLog(this.loginLogSelectRow).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '登录日志信息删除成功.', duration: 3 })
          this.setLoginLogSelectRow([])
          this.getLoginLogSet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.loginLogSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.loginLogSpinShow = false }, 700)
      })
    },
    detailLoginLog () {
      this.loginLogDetailDrawerShow = true
      let loginLogDetail = new LoginLog()
      loginLogDetail.logId = this.loginLogSelectRow[0].logId
      this.getLoginLogByPk(loginLogDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.loginLog, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    dblclickDetailLoginLog (rowData) {
      this.loginLogDetailDrawerShow = true
      let loginLogDetail = new LoginLog()
      loginLogDetail.logId = rowData.logId
      this.getLoginLogByPk(loginLogDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.loginLog, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getLoginLogSet () {
      this.loginLogSpinShow = true
      this.loginLogPageShow = true
      this.pagingQueryLoginLog({ loginLogQuery: {}, rows: this.loginLogRows, page: this.loginLogPage }).then(res => {
        if (res.data.success) {
          this.loginLogSet = res.data.data
          this.loginLogCount = res.data.count
          this.setLoginLogSelectRow([])
          setTimeout(() => { this.loginLogSpinShow = false }, 700)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.loginLogSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.loginLogSpinShow = false }, 700)
      })
    },
    keySearchLoginLog (keyLikeValue) {
      if (keyLikeValue !== '') {
        this.loginLogKeySearchSubmitting = true
        this.loginLogPageShow = false
        this.loginLogSpinShow = true
        let loginLogQueryKeySearch = new LoginLogQuery()
        loginLogQueryKeySearch.loginKeyAndKeyLike = '%' + keyLikeValue + '%'
        loginLogQueryKeySearch.accountIdOrKeyLike = '%' + keyLikeValue + '%'
        loginLogQueryKeySearch.accountOrKeyLike = '%' + keyLikeValue + '%'
        loginLogQueryKeySearch.mobilePhoneNumberOrKeyLike = '%' + keyLikeValue + '%'
        this.queryLoginLog(loginLogQueryKeySearch).then(res => {
          if (res.data.success) {
            this.loginLogSet = res.data.data
            this.loginLogCount = res.data.count
            this.setLoginLogSelectRow([])
            setTimeout(() => { this.loginLogSpinShow = false }, 700)
            setTimeout(() => { this.loginLogKeySearchSubmitting = false }, 5000)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.loginLogSpinShow = false }, 700)
            setTimeout(() => { this.loginLogKeySearchSubmitting = false }, 5000)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.loginLogSpinShow = false }, 700)
          setTimeout(() => { this.loginLogKeySearchSubmitting = false }, 5000)
        })
      } else {
        this.loginLogKeySearchClickRate++
        if (this.loginLogKeySearchClickRate > 10) {
          this.$Message.warning({ content: '兄dei，你是闲的吗？', duration: 6 })
          this.loginLogKeySearchClickRate = 0
        }
      }
    },
    vagueQueryLoginLog () {
      this.loginLogQuerySubmitting = true
      this.loginLogPageShow = false
      this.loginLogSpinShow = true
      this.loginLogQueryPoptipShow = false
      this.queryLoginLog(this.loginLogQuery).then(res => {
        if (res.data.success) {
          this.loginLogSet = res.data.data
          this.loginLogCount = res.data.count
          this.setLoginLogSelectRow([])
          setTimeout(() => { this.loginLogSpinShow = false }, 700)
          setTimeout(() => { this.loginLogQuerySubmitting = false }, 5000)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.loginLogSpinShow = false }, 700)
          setTimeout(() => { this.loginLogQuerySubmitting = false }, 5000)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.loginLogSpinShow = false }, 700)
        setTimeout(() => { this.loginLogQuerySubmitting = false }, 5000)
      })
    },
    resetLoginLogQueryForm () {
      this.$refs.loginLogQueryForm.resetFields()
    },
    setLoginLogSelectRow (list) {
      this.loginLogSelectRow = list
      this.loginLogSelectRowLength = list.length
    },
    loginLogPageIndexChange (pageIndex) {
      this.loginLogPage = pageIndex
      this.getLoginLogSet()
    },
    loginLogPageSizeChange (pageSize) {
      this.loginLogRows = pageSize
      this.loginLogPage = 1
      this.getLoginLogSet()
    },
    loginLogRefresh () {
      this.loginLogPage = 1
      this.getLoginLogSet()
    }
  },
  mounted () {
    this.getLoginLogSet()
  }
}
</script>
