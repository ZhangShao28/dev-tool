<style lang="less" scoped>
  @import './virtualColumns.less';
</style>

<template>
  <div>
    <Card>
      <Row>
        <Col :xs="16" :sm="14" :md="10" :lg="6">
          <Input placeholder="关键字搜索" search enter-button :disabled="virtualColumnsKeySearchSubmitting" @on-search="keySearchVirtualColumns($event)" />
        </Col>
        <Col :xs="8" :sm="10" :md="14" :lg="18">
          <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="900" v-model="virtualColumnsQueryPoptipShow">
            <Button icon="ios-locate-outline">精准查询</Button>
            <div slot="content">
              <Form class="pro-margin-top-10" ref="virtualColumnsQueryForm" :model="virtualColumnsQuery" :label-width="120">
                <Row>
                  <Col span="8">
                    <FormItem label="源表数据库表编号：" prop="sourceDtId">
                      <Input v-model="virtualColumnsQuery.sourceDtId" placeholder="输入源表数据库表编号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="目标表数据库表编号：" prop="targetDtId">
                      <Input v-model="virtualColumnsQuery.targetDtId" placeholder="输入目标表数据库表编号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="源表列编号：" prop="sourceColumnsId">
                      <Input v-model="virtualColumnsQuery.sourceColumnsId" placeholder="输入源表列编号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="目标表列编号：" prop="targetColumnsId">
                      <Input v-model="virtualColumnsQuery.targetColumnsId" placeholder="输入目标表列编号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="目标表显示字段列编号：" prop="targetDisplayColumnsId">
                      <Input v-model="virtualColumnsQuery.targetDisplayColumnsId" placeholder="输入目标表显示字段列编号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="显示字段别名：" prop="displayColumnsAlias">
                      <Input v-model="virtualColumnsQuery.displayColumnsAlias" placeholder="输入显示字段别名"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="虚拟列sql：" prop="virtualColumnsSql">
                      <Input v-model="virtualColumnsQuery.virtualColumnsSql" placeholder="输入虚拟列sql"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="排序权重：" prop="weightOrder">
                      <InputNumber v-model="virtualColumnsQuery.weightOrder"></InputNumber>
                    </FormItem>
                  </Col>
                </Row>
                <Divider />
                <Row>
                  <Col span="24">
                    <FormItem class="pro-float-right">
                      <Button type="primary" @click="vagueQueryVirtualColumns()" :loading="virtualColumnsQuerySubmitting">精准查询</Button>
                      <Button class="pro-margin-left-8" @click="resetVirtualColumnsQueryForm()">重置</Button>
                    </FormItem>
                  </Col>
                </Row>
              </Form>
            </div>
          </Poptip>
        </Col>
      </Row>
      <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addVirtualColumns()">新建</Button>
      <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upVirtualColumns()" v-if="virtualColumnsSelectRowLength === 1">编辑</Button>
      <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delVirtualColumns()" v-if="virtualColumnsSelectRowLength > 0">
        <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
      </Poptip>
      <Button class="pro-list-button" icon="md-bulb" @click="detailVirtualColumns()" v-if="virtualColumnsSelectRowLength === 1">详情</Button>
      <Table :columns="virtualColumnsColumns" :data="virtualColumnsSet" @on-selection-change="setVirtualColumnsSelectRow($event)" @on-row-dblclick="dblclickDetailVirtualColumns($event)"></Table>
      <Row class="pro-margin-top-10" type="flex" justify="center">
        <Page size="small" placement="top" show-total show-sizer show-elevator :total="virtualColumnsCount" :current="virtualColumnsPage" :page-size-opts="[10, 30, 50]" @on-change="virtualColumnsPageIndexChange($event)" @on-page-size-change="virtualColumnsPageSizeChange($event)" v-if="virtualColumnsPageShow" />
        <Button type="text" shape="circle" icon="md-refresh" @click="virtualColumnsRefresh()"></Button>
      </Row>
      <Spin size="large" fix v-if="virtualColumnsSpinShow"></Spin>
    </Card>
    <Modal width="60" v-model="virtualColumnsEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑虚拟列
        </p>
        <Form class="pro-margin-top-20" ref="virtualColumnsForm" :model="virtualColumns" :rules="virtualColumnsFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="源表数据库表编号：" prop="sourceDtId">
                <Input v-model="virtualColumns.sourceDtId" placeholder="输入源表数据库表编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="目标表数据库表编号：" prop="targetDtId">
                <Input v-model="virtualColumns.targetDtId" placeholder="输入目标表数据库表编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="源表列编号：" prop="sourceColumnsId">
                <Input v-model="virtualColumns.sourceColumnsId" placeholder="输入源表列编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="目标表列编号：" prop="targetColumnsId">
                <Input v-model="virtualColumns.targetColumnsId" placeholder="输入目标表列编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="目标表显示字段列编号：" prop="targetDisplayColumnsId">
                <Input v-model="virtualColumns.targetDisplayColumnsId" placeholder="输入目标表显示字段列编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="显示字段别名：" prop="displayColumnsAlias">
                <Input v-model="virtualColumns.displayColumnsAlias" placeholder="输入显示字段别名"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="虚拟列sql：" prop="virtualColumnsSql">
                <Input v-model="virtualColumns.virtualColumnsSql" placeholder="输入虚拟列sql"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="排序权重：" prop="weightOrder">
                <InputNumber v-model="virtualColumns.weightOrder"></InputNumber>
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
            <p>虚拟列编号：{{ virtualColumns.virtualColumnsId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>源表数据库表编号：{{ virtualColumns.sourceDtId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>目标表数据库表编号：{{ virtualColumns.targetDtId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>源表列编号：{{ virtualColumns.sourceColumnsId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>目标表列编号：{{ virtualColumns.targetColumnsId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>目标表显示字段列编号：{{ virtualColumns.targetDisplayColumnsId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>显示字段别名：{{ virtualColumns.displayColumnsAlias }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>虚拟列sql：{{ virtualColumns.virtualColumnsSql }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>排序权重：{{ virtualColumns.weightOrder }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import VirtualColumns from '@/view/cn/moonnow/code/virtualColumns/VirtualColumns'
import VirtualColumnsQuery from '@/view/cn/moonnow/code/virtualColumns/VirtualColumnsQuery'

export default {
  data () {
    return {
      virtualColumnsColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '源表数据库表编号', key: 'sourceDtId', minWidth: 200 },
        { title: '目标表数据库表编号', key: 'targetDtId', minWidth: 200 },
        { title: '源表列编号', key: 'sourceColumnsId', minWidth: 200 },
        { title: '目标表列编号', key: 'targetColumnsId', minWidth: 200 },
        { title: '目标表显示字段列编号', key: 'targetDisplayColumnsId', minWidth: 200 },
        { title: '显示字段别名', key: 'displayColumnsAlias', minWidth: 200 },
        { title: '虚拟列sql', key: 'virtualColumnsSql', minWidth: 200 },
        { title: '排序权重', key: 'weightOrder', minWidth: 200 }
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
      virtualColumnsCount: 0,
      virtualColumnsRows: 10,
      virtualColumnsPage: 1,
      virtualColumnsSelectRow: [],
      virtualColumnsSelectRowLength: 0,
      virtualColumns: new VirtualColumns(),
      virtualColumnsQuery: new VirtualColumnsQuery(),
      virtualColumnsSpinShow: false,
      virtualColumnsPageShow: true,
      virtualColumnsEditModalShow: false,
      virtualColumnsSubmitting: false,
      virtualColumnsContinue: false,
      virtualColumnsDetailDrawerShow: false,
      virtualColumnsQueryPoptipShow: false,
      virtualColumnsQuerySubmitting: false,
      virtualColumnsKeySearchSubmitting: false,
      virtualColumnsKeySearchClickRate: 0
    }
  },
  computed: {
    ...mapState('virtualColumnsStore', [
    ]),
    ...mapGetters('virtualColumnsStore', [
    ])
  },
  methods: {
    ...mapMutations('virtualColumnsStore', [
    ]),
    ...mapActions('virtualColumnsStore', [
      'saveVirtualColumns',
      'updateVirtualColumns',
      'batchRemoveVirtualColumns',
      'getVirtualColumnsByPk',
      'queryVirtualColumns',
      'pagingQueryVirtualColumns'
    ]),
    addVirtualColumns () {
      this.virtualColumnsEditModalShow = true
      this.virtualColumns = new VirtualColumns()
    },
    upVirtualColumns () {
      this.virtualColumnsEditModalShow = true
      let virtualColumnsEdit = new VirtualColumns()
      virtualColumnsEdit.virtualColumnsId = this.virtualColumnsSelectRow[0].virtualColumnsId
      this.getVirtualColumnsByPk(virtualColumnsEdit).then(res => {
        if (res.data.success) {
          Object.assign(this.virtualColumns, res.data.data[0])
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
      this.getVirtualColumnsByPk(virtualColumnsDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.virtualColumns, res.data.data[0])
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
      this.getVirtualColumnsByPk(virtualColumnsDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.virtualColumns, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getVirtualColumnsSet () {
      this.virtualColumnsSpinShow = true
      this.virtualColumnsPageShow = true
      this.pagingQueryVirtualColumns({ virtualColumnsQuery: {}, rows: this.virtualColumnsRows, page: this.virtualColumnsPage }).then(res => {
        if (res.data.success) {
          this.virtualColumnsSet = res.data.data
          this.virtualColumnsCount = res.data.count
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
    },
    keySearchVirtualColumns (keyLikeValue) {
      if (keyLikeValue !== '') {
        this.virtualColumnsKeySearchSubmitting = true
        this.virtualColumnsPageShow = false
        this.virtualColumnsSpinShow = true
        let virtualColumnsQueryKeySearch = new VirtualColumnsQuery()
        virtualColumnsQueryKeySearch.sourceDtIdAndKeyLike = '%' + keyLikeValue + '%'
        virtualColumnsQueryKeySearch.targetDtIdOrKeyLike = '%' + keyLikeValue + '%'
        virtualColumnsQueryKeySearch.sourceColumnsIdOrKeyLike = '%' + keyLikeValue + '%'
        virtualColumnsQueryKeySearch.targetColumnsIdOrKeyLike = '%' + keyLikeValue + '%'
        virtualColumnsQueryKeySearch.targetDisplayColumnsIdOrKeyLike = '%' + keyLikeValue + '%'
        virtualColumnsQueryKeySearch.displayColumnsAliasOrKeyLike = '%' + keyLikeValue + '%'
        virtualColumnsQueryKeySearch.virtualColumnsSqlOrKeyLike = '%' + keyLikeValue + '%'
        this.queryVirtualColumns(virtualColumnsQueryKeySearch).then(res => {
          if (res.data.success) {
            this.virtualColumnsSet = res.data.data
            this.virtualColumnsCount = res.data.count
            this.setVirtualColumnsSelectRow([])
            setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
            setTimeout(() => { this.virtualColumnsKeySearchSubmitting = false }, 5000)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
            setTimeout(() => { this.virtualColumnsKeySearchSubmitting = false }, 5000)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
          setTimeout(() => { this.virtualColumnsKeySearchSubmitting = false }, 5000)
        })
      } else {
        this.virtualColumnsKeySearchClickRate++
        if (this.virtualColumnsKeySearchClickRate > 10) {
          this.$Message.warning({ content: '兄dei，你是闲的吗？', duration: 6 })
          this.virtualColumnsKeySearchClickRate = 0
        }
      }
    },
    vagueQueryVirtualColumns () {
      this.virtualColumnsQuerySubmitting = true
      this.virtualColumnsPageShow = false
      this.virtualColumnsSpinShow = true
      this.virtualColumnsQueryPoptipShow = false
      this.queryVirtualColumns(this.virtualColumnsQuery).then(res => {
        if (res.data.success) {
          this.virtualColumnsSet = res.data.data
          this.virtualColumnsCount = res.data.count
          this.setVirtualColumnsSelectRow([])
          setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
          setTimeout(() => { this.virtualColumnsQuerySubmitting = false }, 5000)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
          setTimeout(() => { this.virtualColumnsQuerySubmitting = false }, 5000)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.virtualColumnsSpinShow = false }, 700)
        setTimeout(() => { this.virtualColumnsQuerySubmitting = false }, 5000)
      })
    },
    resetVirtualColumnsQueryForm () {
      this.$refs.virtualColumnsQueryForm.resetFields()
    },
    setVirtualColumnsSelectRow (list) {
      this.virtualColumnsSelectRow = list
      this.virtualColumnsSelectRowLength = list.length
    },
    virtualColumnsPageIndexChange (pageIndex) {
      this.virtualColumnsPage = pageIndex
      this.getVirtualColumnsSet()
    },
    virtualColumnsPageSizeChange (pageSize) {
      this.virtualColumnsRows = pageSize
      this.virtualColumnsPage = 1
      this.getVirtualColumnsSet()
    },
    virtualColumnsRefresh () {
      this.virtualColumnsPage = 1
      this.getVirtualColumnsSet()
    }
  },
  mounted () {
    this.getVirtualColumnsSet()
  }
}
</script>
