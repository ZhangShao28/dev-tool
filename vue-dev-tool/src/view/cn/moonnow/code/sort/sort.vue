<style lang="less" scoped>
  @import './sort.less';
</style>

<template>
  <div>
    <Card>
      <Row>
        <Col :xs="16" :sm="14" :md="10" :lg="6">
          <Input placeholder="关键字搜索" search enter-button :disabled="sortKeySearchSubmitting" @on-search="keySearchSort($event)" />
        </Col>
        <Col :xs="8" :sm="10" :md="14" :lg="18">
          <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="900" v-model="sortQueryPoptipShow">
            <Button icon="ios-locate-outline">精准查询</Button>
            <div slot="content">
              <Form class="pro-margin-top-10" ref="sortQueryForm" :model="sortQuery" :label-width="120">
                <Row>
                  <Col span="8">
                    <FormItem label="排序规则：" prop="sortRule">
                      <Input v-model="sortQuery.sortRule" placeholder="输入排序规则"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="数据库表编号：" prop="dtId">
                      <Input v-model="sortQuery.dtId" placeholder="输入数据库表编号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="列编号：" prop="columnsId">
                      <Input v-model="sortQuery.columnsId" placeholder="输入列编号"></Input>
                    </FormItem>
                  </Col>
                </Row>
                <Divider />
                <Row>
                  <Col span="24">
                    <FormItem class="pro-float-right">
                      <Button type="primary" @click="vagueQuerySort()" :loading="sortQuerySubmitting">精准查询</Button>
                      <Button class="pro-margin-left-8" @click="resetSortQueryForm()">重置</Button>
                    </FormItem>
                  </Col>
                </Row>
              </Form>
            </div>
          </Poptip>
        </Col>
      </Row>
      <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addSort()">新建</Button>
      <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upSort()" v-if="sortSelectRowLength === 1">编辑</Button>
      <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delSort()" v-if="sortSelectRowLength > 0">
        <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
      </Poptip>
      <Button class="pro-list-button" icon="md-bulb" @click="detailSort()" v-if="sortSelectRowLength === 1">详情</Button>
      <Table :columns="sortColumns" :data="sortSet" @on-selection-change="setSortSelectRow($event)" @on-row-dblclick="dblclickDetailSort($event)"></Table>
      <Row class="pro-margin-top-10" type="flex" justify="center">
        <Page size="small" placement="top" show-total show-sizer show-elevator :total="sortCount" :current="sortPage" :page-size-opts="[10, 30, 50]" @on-change="sortPageIndexChange($event)" @on-page-size-change="sortPageSizeChange($event)" v-if="sortPageShow" />
        <Button type="text" shape="circle" icon="md-refresh" @click="sortRefresh()"></Button>
      </Row>
      <Spin size="large" fix v-if="sortSpinShow"></Spin>
    </Card>
    <Modal width="60" v-model="sortEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑排序
        </p>
        <Form class="pro-margin-top-20" ref="sortForm" :model="sort" :rules="sortFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="排序规则：" prop="sortRule">
                <Input v-model="sort.sortRule" placeholder="输入排序规则"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="数据库表编号：" prop="dtId">
                <Input v-model="sort.dtId" placeholder="输入数据库表编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="列编号：" prop="columnsId">
                <Input v-model="sort.columnsId" placeholder="输入列编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem>
                <Button class="pro-margin-right-8" @click="toSortList()">取消</Button>
                <Button class="pro-margin-right-8" @click="resetSortForm()">重置</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="sortSubmitting" @click="saveSortForm()">保存</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="sortSubmitting" @click="saveAddSortForm()">继续录入</Button>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    </Modal>
    <Drawer width="45" v-model="sortDetailDrawerShow" :closable="false">
      <Card>
        <p slot="title">
          <Icon type="md-bulb"></Icon>
          排序详情
        </p>
        <Row>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>排序编号：{{ sort.sortId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>排序规则：{{ sort.sortRule }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据库表编号：{{ sort.dtId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>列编号：{{ sort.columnsId }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import Sort from '@/view/cn/moonnow/code/sort/Sort'
import SortQuery from '@/view/cn/moonnow/code/sort/SortQuery'

export default {
  data () {
    return {
      sortColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '排序规则', key: 'sortRule', minWidth: 200 },
        { title: '数据库表编号', key: 'dtId', minWidth: 200 },
        { title: '列编号', key: 'columnsId', minWidth: 200 }
      ],
      sortFormValidate: {
        sortRule: [
          { required: true, message: '排序规则不能为空', trigger: 'blur' }
        ],
        dtId: [
          { required: true, message: '数据库表编号不能为空', trigger: 'blur' }
        ],
        columnsId: [
          { required: true, message: '列编号不能为空', trigger: 'blur' }
        ]
      },
      sortSet: [],
      sortCount: 0,
      sortRows: 10,
      sortPage: 1,
      sortSelectRow: [],
      sortSelectRowLength: 0,
      sort: new Sort(),
      sortQuery: new SortQuery(),
      sortSpinShow: false,
      sortPageShow: true,
      sortEditModalShow: false,
      sortSubmitting: false,
      sortContinue: false,
      sortDetailDrawerShow: false,
      sortQueryPoptipShow: false,
      sortQuerySubmitting: false,
      sortKeySearchSubmitting: false,
      sortKeySearchClickRate: 0
    }
  },
  computed: {
    ...mapState('sortStore', [
    ]),
    ...mapGetters('sortStore', [
    ])
  },
  methods: {
    ...mapMutations('sortStore', [
    ]),
    ...mapActions('sortStore', [
      'saveSort',
      'updateSort',
      'batchRemoveSort',
      'getSortByPk',
      'querySort',
      'pagingQuerySort'
    ]),
    addSort () {
      this.sortEditModalShow = true
      this.sort = new Sort()
    },
    upSort () {
      this.sortEditModalShow = true
      let sortEdit = new Sort()
      sortEdit.sortId = this.sortSelectRow[0].sortId
      this.getSortByPk(sortEdit).then(res => {
        if (res.data.success) {
          Object.assign(this.sort, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toSortList () {
      this.sortEditModalShow = false
      this.resetSortForm()
      this.getSortSet()
    },
    resetSortForm () {
      this.$refs.sortForm.resetFields()
    },
    saveSortForm () {
      this.sortContinue = false
      this.submitSortForm()
    },
    saveAddSortForm () {
      this.sortContinue = true
      this.submitSortForm()
    },
    submitSortForm () {
      this.sortSubmitting = true
      this.$refs.sortForm.validate((valid) => {
        if (valid) {
          if (this.sort.sortId) {
            this.updateSort(this.sort).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '排序信息修改成功.', duration: 3 })
                this.resetSortForm()
                this.sort = new Sort()
                this.sortSubmitting = false
                if (!this.sortContinue) {
                  this.toSortList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.sortSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.sortSubmitting = false }, 3000)
            })
          } else {
            this.saveSort(this.sort).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '排序信息保存成功.', duration: 3 })
                this.resetSortForm()
                this.sort = new Sort()
                this.sortSubmitting = false
                if (!this.sortContinue) {
                  this.toSortList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.sortSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.sortSubmitting = false }, 3000)
            })
          }
        } else {
          setTimeout(() => { this.sortSubmitting = false }, 3000)
        }
      })
    },
    delSort () {
      this.sortSpinShow = true
      this.batchRemoveSort(this.sortSelectRow).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '排序信息删除成功.', duration: 3 })
          this.setSortSelectRow([])
          this.getSortSet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.sortSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.sortSpinShow = false }, 700)
      })
    },
    detailSort () {
      this.sortDetailDrawerShow = true
      let sortDetail = new Sort()
      sortDetail.sortId = this.sortSelectRow[0].sortId
      this.getSortByPk(sortDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.sort, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    dblclickDetailSort (rowData) {
      this.sortDetailDrawerShow = true
      let sortDetail = new Sort()
      sortDetail.sortId = rowData.sortId
      this.getSortByPk(sortDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.sort, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getSortSet () {
      this.sortSpinShow = true
      this.sortPageShow = true
      this.pagingQuerySort({ sortQuery: {}, rows: this.sortRows, page: this.sortPage }).then(res => {
        if (res.data.success) {
          this.sortSet = res.data.data
          this.sortCount = res.data.count
          this.setSortSelectRow([])
          setTimeout(() => { this.sortSpinShow = false }, 700)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.sortSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.sortSpinShow = false }, 700)
      })
    },
    keySearchSort (keyLikeValue) {
      if (keyLikeValue !== '') {
        this.sortKeySearchSubmitting = true
        this.sortPageShow = false
        this.sortSpinShow = true
        let sortQueryKeySearch = new SortQuery()
        sortQueryKeySearch.sortRuleAndKeyLike = '%' + keyLikeValue + '%'
        sortQueryKeySearch.dtIdOrKeyLike = '%' + keyLikeValue + '%'
        sortQueryKeySearch.columnsIdOrKeyLike = '%' + keyLikeValue + '%'
        this.querySort(sortQueryKeySearch).then(res => {
          if (res.data.success) {
            this.sortSet = res.data.data
            this.sortCount = res.data.count
            this.setSortSelectRow([])
            setTimeout(() => { this.sortSpinShow = false }, 700)
            setTimeout(() => { this.sortKeySearchSubmitting = false }, 5000)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.sortSpinShow = false }, 700)
            setTimeout(() => { this.sortKeySearchSubmitting = false }, 5000)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.sortSpinShow = false }, 700)
          setTimeout(() => { this.sortKeySearchSubmitting = false }, 5000)
        })
      } else {
        this.sortKeySearchClickRate++
        if (this.sortKeySearchClickRate > 10) {
          this.$Message.warning({ content: '兄dei，你是闲的吗？', duration: 6 })
          this.sortKeySearchClickRate = 0
        }
      }
    },
    vagueQuerySort () {
      this.sortQuerySubmitting = true
      this.sortPageShow = false
      this.sortSpinShow = true
      this.sortQueryPoptipShow = false
      this.querySort(this.sortQuery).then(res => {
        if (res.data.success) {
          this.sortSet = res.data.data
          this.sortCount = res.data.count
          this.setSortSelectRow([])
          setTimeout(() => { this.sortSpinShow = false }, 700)
          setTimeout(() => { this.sortQuerySubmitting = false }, 5000)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.sortSpinShow = false }, 700)
          setTimeout(() => { this.sortQuerySubmitting = false }, 5000)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.sortSpinShow = false }, 700)
        setTimeout(() => { this.sortQuerySubmitting = false }, 5000)
      })
    },
    resetSortQueryForm () {
      this.$refs.sortQueryForm.resetFields()
    },
    setSortSelectRow (list) {
      this.sortSelectRow = list
      this.sortSelectRowLength = list.length
    },
    sortPageIndexChange (pageIndex) {
      this.sortPage = pageIndex
      this.getSortSet()
    },
    sortPageSizeChange (pageSize) {
      this.sortRows = pageSize
      this.sortPage = 1
      this.getSortSet()
    },
    sortRefresh () {
      this.sortPage = 1
      this.getSortSet()
    }
  },
  mounted () {
    this.getSortSet()
  }
}
</script>
