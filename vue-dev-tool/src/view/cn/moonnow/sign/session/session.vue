<style lang="less" scoped>
  @import './session.less';
</style>

<template>
  <div>
    <Card>
      <Row>
        <Col :xs="16" :sm="14" :md="10" :lg="6">
          <Input placeholder="关键字搜索" search enter-button :disabled="sessionKeySearchSubmitting" @on-search="keySearchSession($event)" />
        </Col>
        <Col :xs="8" :sm="10" :md="14" :lg="18">
          <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="900" v-model="sessionQueryPoptipShow">
            <Button icon="ios-locate-outline">精准查询</Button>
            <div slot="content">
              <Form class="pro-margin-top-10" ref="sessionQueryForm" :model="sessionQuery" :label-width="120">
                <Row>
                  <Col span="8">
                    <FormItem label="账号编号：" prop="accountId">
                      <Input v-model="sessionQuery.accountId" placeholder="输入账号编号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="登录标识：" prop="loginKey">
                      <Input v-model="sessionQuery.loginKey" placeholder="输入登录标识"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="当前时间：" prop="currentTimes">
                      <InputNumber v-model="sessionQuery.currentTimes"></InputNumber>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="AES密钥：" prop="aesKey">
                      <Input v-model="sessionQuery.aesKey" placeholder="输入AES密钥"></Input>
                    </FormItem>
                  </Col>
                </Row>
                <Divider />
                <Row>
                  <Col span="24">
                    <FormItem class="pro-float-right">
                      <Button type="primary" @click="vagueQuerySession()" :loading="sessionQuerySubmitting">精准查询</Button>
                      <Button class="pro-margin-left-8" @click="resetSessionQueryForm()">重置</Button>
                    </FormItem>
                  </Col>
                </Row>
              </Form>
            </div>
          </Poptip>
        </Col>
      </Row>
      <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addSession()">新建</Button>
      <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upSession()" v-if="sessionSelectRowLength === 1">编辑</Button>
      <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delSession()" v-if="sessionSelectRowLength > 0">
        <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
      </Poptip>
      <Button class="pro-list-button" icon="md-bulb" @click="detailSession()" v-if="sessionSelectRowLength === 1">详情</Button>
      <Table :columns="sessionColumns" :data="sessionSet" @on-selection-change="setSessionSelectRow($event)" @on-row-dblclick="dblclickDetailSession($event)"></Table>
      <Row class="pro-margin-top-10" type="flex" justify="center">
        <Page size="small" placement="top" show-total show-sizer show-elevator :total="sessionCount" :current="sessionPage" :page-size-opts="[10, 30, 50]" @on-change="sessionPageIndexChange($event)" @on-page-size-change="sessionPageSizeChange($event)" v-if="sessionPageShow" />
        <Button type="text" shape="circle" icon="md-refresh" @click="sessionRefresh()"></Button>
      </Row>
      <Spin size="large" fix v-if="sessionSpinShow"></Spin>
    </Card>
    <Modal width="60" v-model="sessionEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑会话
        </p>
        <Form class="pro-margin-top-20" ref="sessionForm" :model="session" :rules="sessionFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="账号编号：" prop="accountId">
                <Input v-model="session.accountId" placeholder="输入账号编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="登录标识：" prop="loginKey">
                <Input v-model="session.loginKey" placeholder="输入登录标识"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="当前时间：" prop="currentTimes">
                <InputNumber v-model="session.currentTimes"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="AES密钥：" prop="aesKey">
                <Input v-model="session.aesKey" placeholder="输入AES密钥"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem>
                <Button class="pro-margin-right-8" @click="toSessionList()">取消</Button>
                <Button class="pro-margin-right-8" @click="resetSessionForm()">重置</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="sessionSubmitting" @click="saveSessionForm()">保存</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="sessionSubmitting" @click="saveAddSessionForm()">继续录入</Button>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    </Modal>
    <Drawer width="45" v-model="sessionDetailDrawerShow" :closable="false">
      <Card>
        <p slot="title">
          <Icon type="md-bulb"></Icon>
          会话详情
        </p>
        <Row>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>会话编号：{{ session.sessionId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>账号编号：{{ session.accountId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>登录标识：{{ session.loginKey }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>当前时间：{{ session.currentTimes }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>AES密钥：{{ session.aesKey }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import Session from '@/view/cn/moonnow/sign/session/Session'
import SessionQuery from '@/view/cn/moonnow/sign/session/SessionQuery'

export default {
  data () {
    return {
      sessionColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '账号编号', key: 'accountId', minWidth: 200 },
        { title: '登录标识', key: 'loginKey', minWidth: 200 },
        { title: '当前时间', key: 'currentTimes', minWidth: 200 },
        { title: 'AES密钥', key: 'aesKey', minWidth: 200 }
      ],
      sessionFormValidate: {
        accountId: [
          { required: true, message: '账号编号不能为空', trigger: 'blur' }
        ],
        loginKey: [
          { required: true, message: '登录标识不能为空', trigger: 'blur' }
        ],
        currentTimes: [
          { type: 'number', required: true, message: '当前时间不能为空', trigger: 'change' }
        ],
        aesKey: [
          { required: true, message: 'AES密钥不能为空', trigger: 'blur' }
        ]
      },
      sessionSet: [],
      sessionCount: 0,
      sessionRows: 10,
      sessionPage: 1,
      sessionSelectRow: [],
      sessionSelectRowLength: 0,
      session: new Session(),
      sessionQuery: new SessionQuery(),
      sessionSpinShow: false,
      sessionPageShow: true,
      sessionEditModalShow: false,
      sessionSubmitting: false,
      sessionContinue: false,
      sessionDetailDrawerShow: false,
      sessionQueryPoptipShow: false,
      sessionQuerySubmitting: false,
      sessionKeySearchSubmitting: false,
      sessionKeySearchClickRate: 0
    }
  },
  computed: {
    ...mapState('sessionStore', [
    ]),
    ...mapGetters('sessionStore', [
    ])
  },
  methods: {
    ...mapMutations('sessionStore', [
    ]),
    ...mapActions('sessionStore', [
      'saveSession',
      'updateSession',
      'batchRemoveSession',
      'getSessionByPk',
      'querySession',
      'pagingQuerySession'
    ]),
    addSession () {
      this.sessionEditModalShow = true
      this.session = new Session()
    },
    upSession () {
      this.sessionEditModalShow = true
      let sessionEdit = new Session()
      sessionEdit.sessionId = this.sessionSelectRow[0].sessionId
      this.getSessionByPk(sessionEdit).then(res => {
        if (res.data.success) {
          Object.assign(this.session, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toSessionList () {
      this.sessionEditModalShow = false
      this.resetSessionForm()
      this.getSessionSet()
    },
    resetSessionForm () {
      this.$refs.sessionForm.resetFields()
    },
    saveSessionForm () {
      this.sessionContinue = false
      this.submitSessionForm()
    },
    saveAddSessionForm () {
      this.sessionContinue = true
      this.submitSessionForm()
    },
    submitSessionForm () {
      this.sessionSubmitting = true
      this.$refs.sessionForm.validate((valid) => {
        if (valid) {
          if (this.session.sessionId) {
            this.updateSession(this.session).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '会话信息修改成功.', duration: 3 })
                this.resetSessionForm()
                this.session = new Session()
                this.sessionSubmitting = false
                if (!this.sessionContinue) {
                  this.toSessionList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.sessionSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.sessionSubmitting = false }, 3000)
            })
          } else {
            this.saveSession(this.session).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '会话信息保存成功.', duration: 3 })
                this.resetSessionForm()
                this.session = new Session()
                this.sessionSubmitting = false
                if (!this.sessionContinue) {
                  this.toSessionList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.sessionSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.sessionSubmitting = false }, 3000)
            })
          }
        } else {
          setTimeout(() => { this.sessionSubmitting = false }, 3000)
        }
      })
    },
    delSession () {
      this.sessionSpinShow = true
      this.batchRemoveSession(this.sessionSelectRow).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '会话信息删除成功.', duration: 3 })
          this.setSessionSelectRow([])
          this.getSessionSet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.sessionSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.sessionSpinShow = false }, 700)
      })
    },
    detailSession () {
      this.sessionDetailDrawerShow = true
      let sessionDetail = new Session()
      sessionDetail.sessionId = this.sessionSelectRow[0].sessionId
      this.getSessionByPk(sessionDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.session, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    dblclickDetailSession (rowData) {
      this.sessionDetailDrawerShow = true
      let sessionDetail = new Session()
      sessionDetail.sessionId = rowData.sessionId
      this.getSessionByPk(sessionDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.session, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getSessionSet () {
      this.sessionSpinShow = true
      this.sessionPageShow = true
      this.pagingQuerySession({ sessionQuery: {}, rows: this.sessionRows, page: this.sessionPage }).then(res => {
        if (res.data.success) {
          this.sessionSet = res.data.data
          this.sessionCount = res.data.count
          this.setSessionSelectRow([])
          setTimeout(() => { this.sessionSpinShow = false }, 700)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.sessionSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.sessionSpinShow = false }, 700)
      })
    },
    keySearchSession (keyLikeValue) {
      if (keyLikeValue !== '') {
        this.sessionKeySearchSubmitting = true
        this.sessionPageShow = false
        this.sessionSpinShow = true
        let sessionQueryKeySearch = new SessionQuery()
        sessionQueryKeySearch.accountIdAndKeyLike = '%' + keyLikeValue + '%'
        sessionQueryKeySearch.loginKeyOrKeyLike = '%' + keyLikeValue + '%'
        sessionQueryKeySearch.aesKeyOrKeyLike = '%' + keyLikeValue + '%'
        this.querySession(sessionQueryKeySearch).then(res => {
          if (res.data.success) {
            this.sessionSet = res.data.data
            this.sessionCount = res.data.count
            this.setSessionSelectRow([])
            setTimeout(() => { this.sessionSpinShow = false }, 700)
            setTimeout(() => { this.sessionKeySearchSubmitting = false }, 5000)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.sessionSpinShow = false }, 700)
            setTimeout(() => { this.sessionKeySearchSubmitting = false }, 5000)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.sessionSpinShow = false }, 700)
          setTimeout(() => { this.sessionKeySearchSubmitting = false }, 5000)
        })
      } else {
        this.sessionKeySearchClickRate++
        if (this.sessionKeySearchClickRate > 10) {
          this.$Message.warning({ content: '兄dei，你是闲的吗？', duration: 6 })
          this.sessionKeySearchClickRate = 0
        }
      }
    },
    vagueQuerySession () {
      this.sessionQuerySubmitting = true
      this.sessionPageShow = false
      this.sessionSpinShow = true
      this.sessionQueryPoptipShow = false
      this.querySession(this.sessionQuery).then(res => {
        if (res.data.success) {
          this.sessionSet = res.data.data
          this.sessionCount = res.data.count
          this.setSessionSelectRow([])
          setTimeout(() => { this.sessionSpinShow = false }, 700)
          setTimeout(() => { this.sessionQuerySubmitting = false }, 5000)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.sessionSpinShow = false }, 700)
          setTimeout(() => { this.sessionQuerySubmitting = false }, 5000)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.sessionSpinShow = false }, 700)
        setTimeout(() => { this.sessionQuerySubmitting = false }, 5000)
      })
    },
    resetSessionQueryForm () {
      this.$refs.sessionQueryForm.resetFields()
    },
    setSessionSelectRow (list) {
      this.sessionSelectRow = list
      this.sessionSelectRowLength = list.length
    },
    sessionPageIndexChange (pageIndex) {
      this.sessionPage = pageIndex
      this.getSessionSet()
    },
    sessionPageSizeChange (pageSize) {
      this.sessionRows = pageSize
      this.sessionPage = 1
      this.getSessionSet()
    },
    sessionRefresh () {
      this.sessionPage = 1
      this.getSessionSet()
    }
  },
  mounted () {
    this.getSessionSet()
  }
}
</script>
