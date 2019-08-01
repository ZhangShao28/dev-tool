<style lang="less" scoped>
  @import './grp.less';
</style>

<template>
  <div>
    <Card>
      <Row>
        <Col :xs="16" :sm="14" :md="10" :lg="6">
          <Input placeholder="关键字搜索" search enter-button :disabled="grpKeySearchSubmitting" @on-search="keySearchGrp($event)" />
        </Col>
        <Col :xs="8" :sm="10" :md="14" :lg="18">
          <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="900" v-model="grpQueryPoptipShow">
            <Button icon="ios-locate-outline">精准查询</Button>
            <div slot="content">
              <Form class="pro-margin-top-10" ref="grpQueryForm" :model="grpQuery" :label-width="120">
                <Row>
                  <Col span="8">
                    <FormItem label="组标识：" prop="grpKey">
                      <Input v-model="grpQuery.grpKey" placeholder="输入组标识"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="组名称：" prop="grpName">
                      <Input v-model="grpQuery.grpName" placeholder="输入组名称"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="说明：" prop="notes">
                      <Input v-model="grpQuery.notes" placeholder="输入说明"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="版本号：" prop="versions">
                      <InputNumber v-model="grpQuery.versions"></InputNumber>
                    </FormItem>
                  </Col>
                </Row>
                <Divider />
                <Row>
                  <Col span="24">
                    <FormItem class="pro-float-right">
                      <Button type="primary" @click="vagueQueryGrp()" :loading="grpQuerySubmitting">精准查询</Button>
                      <Button class="pro-margin-left-8" @click="resetGrpQueryForm()">重置</Button>
                    </FormItem>
                  </Col>
                </Row>
              </Form>
            </div>
          </Poptip>
        </Col>
      </Row>
      <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addGrp()">新建</Button>
      <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upGrp()" v-if="grpSelectRowLength === 1">编辑</Button>
      <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delGrp()" v-if="grpSelectRowLength > 0">
        <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
      </Poptip>
      <Button class="pro-list-button" icon="md-bulb" @click="detailGrp()" v-if="grpSelectRowLength === 1">详情</Button>
      <Table :columns="grpColumns" :data="grpSet" @on-selection-change="setGrpSelectRow($event)" @on-row-dblclick="dblclickDetailGrp($event)"></Table>
      <Row class="pro-margin-top-10" type="flex" justify="center">
        <Page size="small" placement="top" show-total show-sizer show-elevator :total="grpCount" :current="grpPage" :page-size-opts="[10, 30, 50]" @on-change="grpPageIndexChange($event)" @on-page-size-change="grpPageSizeChange($event)" v-if="grpPageShow" />
        <Button type="text" shape="circle" icon="md-refresh" @click="grpRefresh()"></Button>
      </Row>
      <Spin size="large" fix v-if="grpSpinShow"></Spin>
    </Card>
    <Modal width="60" v-model="grpEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑组
        </p>
        <Form class="pro-margin-top-20" ref="grpForm" :model="grp" :rules="grpFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="组标识：" prop="grpKey">
                <Input v-model="grp.grpKey" placeholder="输入组标识"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="组名称：" prop="grpName">
                <Input v-model="grp.grpName" placeholder="输入组名称"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="说明：" prop="notes">
                <Input v-model="grp.notes" placeholder="输入说明"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="版本号：" prop="versions">
                <InputNumber v-model="grp.versions"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem>
                <Button class="pro-margin-right-8" @click="toGrpList()">取消</Button>
                <Button class="pro-margin-right-8" @click="resetGrpForm()">重置</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="grpSubmitting" @click="saveGrpForm()">保存</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="grpSubmitting" @click="saveAddGrpForm()">继续录入</Button>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    </Modal>
    <Drawer width="45" v-model="grpDetailDrawerShow" :closable="false">
      <Card>
        <p slot="title">
          <Icon type="md-bulb"></Icon>
          组详情
        </p>
        <Row>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>组编号：{{ grp.grpId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>组标识：{{ grp.grpKey }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>组名称：{{ grp.grpName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>说明：{{ grp.notes }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>版本号：{{ grp.versions }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import Grp from '@/view/cn/moonnow/sign/grp/Grp'
import GrpQuery from '@/view/cn/moonnow/sign/grp/GrpQuery'

export default {
  data () {
    return {
      grpColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '组标识', key: 'grpKey', minWidth: 200 },
        { title: '组名称', key: 'grpName', minWidth: 200 },
        { title: '说明', key: 'notes', minWidth: 200 },
        { title: '版本号', key: 'versions', minWidth: 200 }
      ],
      grpFormValidate: {
        grpKey: [
          { required: true, message: '组标识不能为空', trigger: 'blur' }
        ],
        versions: [
          { type: 'number', required: true, message: '版本号不能为空', trigger: 'change' }
        ]
      },
      grpSet: [],
      grpCount: 0,
      grpRows: 10,
      grpPage: 1,
      grpSelectRow: [],
      grpSelectRowLength: 0,
      grp: new Grp(),
      grpQuery: new GrpQuery(),
      grpSpinShow: false,
      grpPageShow: true,
      grpEditModalShow: false,
      grpSubmitting: false,
      grpContinue: false,
      grpDetailDrawerShow: false,
      grpQueryPoptipShow: false,
      grpQuerySubmitting: false,
      grpKeySearchSubmitting: false,
      grpKeySearchClickRate: 0
    }
  },
  computed: {
    ...mapState('grpStore', [
    ]),
    ...mapGetters('grpStore', [
    ])
  },
  methods: {
    ...mapMutations('grpStore', [
    ]),
    ...mapActions('grpStore', [
      'saveGrp',
      'updateGrp',
      'batchRemoveGrp',
      'getGrpByPk',
      'queryGrp',
      'pagingQueryGrp'
    ]),
    addGrp () {
      this.grpEditModalShow = true
      this.grp = new Grp()
    },
    upGrp () {
      this.grpEditModalShow = true
      let grpEdit = new Grp()
      grpEdit.grpId = this.grpSelectRow[0].grpId
      this.getGrpByPk(grpEdit).then(res => {
        if (res.data.success) {
          Object.assign(this.grp, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toGrpList () {
      this.grpEditModalShow = false
      this.resetGrpForm()
      this.getGrpSet()
    },
    resetGrpForm () {
      this.$refs.grpForm.resetFields()
    },
    saveGrpForm () {
      this.grpContinue = false
      this.submitGrpForm()
    },
    saveAddGrpForm () {
      this.grpContinue = true
      this.submitGrpForm()
    },
    submitGrpForm () {
      this.grpSubmitting = true
      this.$refs.grpForm.validate((valid) => {
        if (valid) {
          if (this.grp.grpId) {
            this.updateGrp(this.grp).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '组信息修改成功.', duration: 3 })
                this.resetGrpForm()
                this.grp = new Grp()
                this.grpSubmitting = false
                if (!this.grpContinue) {
                  this.toGrpList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.grpSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.grpSubmitting = false }, 3000)
            })
          } else {
            this.saveGrp(this.grp).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '组信息保存成功.', duration: 3 })
                this.resetGrpForm()
                this.grp = new Grp()
                this.grpSubmitting = false
                if (!this.grpContinue) {
                  this.toGrpList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.grpSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.grpSubmitting = false }, 3000)
            })
          }
        } else {
          setTimeout(() => { this.grpSubmitting = false }, 3000)
        }
      })
    },
    delGrp () {
      this.grpSpinShow = true
      this.batchRemoveGrp(this.grpSelectRow).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '组信息删除成功.', duration: 3 })
          this.setGrpSelectRow([])
          this.getGrpSet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.grpSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.grpSpinShow = false }, 700)
      })
    },
    detailGrp () {
      this.grpDetailDrawerShow = true
      let grpDetail = new Grp()
      grpDetail.grpId = this.grpSelectRow[0].grpId
      this.getGrpByPk(grpDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.grp, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    dblclickDetailGrp (rowData) {
      this.grpDetailDrawerShow = true
      let grpDetail = new Grp()
      grpDetail.grpId = rowData.grpId
      this.getGrpByPk(grpDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.grp, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getGrpSet () {
      this.grpSpinShow = true
      this.grpPageShow = true
      this.pagingQueryGrp({ grpQuery: {}, rows: this.grpRows, page: this.grpPage }).then(res => {
        if (res.data.success) {
          this.grpSet = res.data.data
          this.grpCount = res.data.count
          this.setGrpSelectRow([])
          setTimeout(() => { this.grpSpinShow = false }, 700)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.grpSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.grpSpinShow = false }, 700)
      })
    },
    keySearchGrp (keyLikeValue) {
      if (keyLikeValue !== '') {
        this.grpKeySearchSubmitting = true
        this.grpPageShow = false
        this.grpSpinShow = true
        let grpQueryKeySearch = new GrpQuery()
        grpQueryKeySearch.grpKeyAndKeyLike = '%' + keyLikeValue + '%'
        grpQueryKeySearch.grpNameOrKeyLike = '%' + keyLikeValue + '%'
        grpQueryKeySearch.notesOrKeyLike = '%' + keyLikeValue + '%'
        this.queryGrp(grpQueryKeySearch).then(res => {
          if (res.data.success) {
            this.grpSet = res.data.data
            this.grpCount = res.data.count
            this.setGrpSelectRow([])
            setTimeout(() => { this.grpSpinShow = false }, 700)
            setTimeout(() => { this.grpKeySearchSubmitting = false }, 5000)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.grpSpinShow = false }, 700)
            setTimeout(() => { this.grpKeySearchSubmitting = false }, 5000)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.grpSpinShow = false }, 700)
          setTimeout(() => { this.grpKeySearchSubmitting = false }, 5000)
        })
      } else {
        this.grpKeySearchClickRate++
        if (this.grpKeySearchClickRate > 10) {
          this.$Message.warning({ content: '兄dei，你是闲的吗？', duration: 6 })
          this.grpKeySearchClickRate = 0
        }
      }
    },
    vagueQueryGrp () {
      this.grpQuerySubmitting = true
      this.grpPageShow = false
      this.grpSpinShow = true
      this.grpQueryPoptipShow = false
      this.queryGrp(this.grpQuery).then(res => {
        if (res.data.success) {
          this.grpSet = res.data.data
          this.grpCount = res.data.count
          this.setGrpSelectRow([])
          setTimeout(() => { this.grpSpinShow = false }, 700)
          setTimeout(() => { this.grpQuerySubmitting = false }, 5000)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.grpSpinShow = false }, 700)
          setTimeout(() => { this.grpQuerySubmitting = false }, 5000)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.grpSpinShow = false }, 700)
        setTimeout(() => { this.grpQuerySubmitting = false }, 5000)
      })
    },
    resetGrpQueryForm () {
      this.$refs.grpQueryForm.resetFields()
    },
    setGrpSelectRow (list) {
      this.grpSelectRow = list
      this.grpSelectRowLength = list.length
    },
    grpPageIndexChange (pageIndex) {
      this.grpPage = pageIndex
      this.getGrpSet()
    },
    grpPageSizeChange (pageSize) {
      this.grpRows = pageSize
      this.grpPage = 1
      this.getGrpSet()
    },
    grpRefresh () {
      this.grpPage = 1
      this.getGrpSet()
    }
  },
  mounted () {
    this.getGrpSet()
  }
}
</script>
