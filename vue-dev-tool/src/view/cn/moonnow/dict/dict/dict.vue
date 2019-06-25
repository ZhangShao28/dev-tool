<style lang="less" scoped>
  @import './dict.less';
</style>

<template>
  <div>
    <Card>
      <Row>
        <Col :xs="16" :sm="14" :md="10" :lg="6">
          <Input placeholder="关键字搜索" search enter-button :disabled="dictKeySearchSubmitting" @on-search="keySearchDict($event)" />
        </Col>
        <Col :xs="8" :sm="10" :md="14" :lg="18">
          <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="900" v-model="dictQueryPoptipShow">
            <Button icon="ios-locate-outline">精准查询</Button>
            <div slot="content">
              <Form class="pro-margin-top-10" ref="dictQueryForm" :model="dictQuery" :label-width="120">
                <Row>
                  <Col span="8">
                    <FormItem label="数据字典标识：" prop="dictKey">
                      <Input v-model="dictQuery.dictKey" placeholder="输入数据字典标识"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="数据字典名称：" prop="dictName">
                      <Input v-model="dictQuery.dictName" placeholder="输入数据字典名称"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="说明：" prop="notes">
                      <Input v-model="dictQuery.notes" placeholder="输入说明"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="排序权重：" prop="weightOrder">
                      <InputNumber v-model="dictQuery.weightOrder"></InputNumber>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="版本号：" prop="versions">
                      <InputNumber v-model="dictQuery.versions"></InputNumber>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="创建人账号：" prop="createId">
                      <Input v-model="dictQuery.createId" placeholder="输入创建人账号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="创建人名称：" prop="createName">
                      <Input v-model="dictQuery.createName" placeholder="输入创建人名称"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="创建时间：" prop="createTimes">
                      <InputNumber v-model="dictQuery.createTimes"></InputNumber>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="修改人账号：" prop="modifyId">
                      <Input v-model="dictQuery.modifyId" placeholder="输入修改人账号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="修改人名称：" prop="modifyName">
                      <Input v-model="dictQuery.modifyName" placeholder="输入修改人名称"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="修改时间：" prop="modifyTimes">
                      <InputNumber v-model="dictQuery.modifyTimes"></InputNumber>
                    </FormItem>
                  </Col>
                </Row>
                <Divider />
                <Row>
                  <Col span="24">
                    <FormItem class="pro-float-right">
                      <Button type="primary" @click="vagueQueryDict()" :loading="dictQuerySubmitting">精准查询</Button>
                      <Button class="pro-margin-left-8" @click="resetDictQueryForm()">重置</Button>
                    </FormItem>
                  </Col>
                </Row>
              </Form>
            </div>
          </Poptip>
        </Col>
      </Row>
      <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addDict()">新建</Button>
      <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upDict()" v-if="dictSelectRowLength === 1">编辑</Button>
      <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delDict()" v-if="dictSelectRowLength > 0">
        <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
      </Poptip>
      <Button class="pro-list-button" icon="md-bulb" @click="detailDict()" v-if="dictSelectRowLength === 1">详情</Button>
      <Table :columns="dictColumns" :data="dictSet" @on-selection-change="setDictSelectRow($event)" @on-row-dblclick="dblclickDetailDict($event)"></Table>
      <Row class="pro-margin-top-10" type="flex" justify="center">
        <Page size="small" placement="top" show-total show-sizer show-elevator :total="dictCount" :current="dictPage" :page-size-opts="[10, 30, 50]" @on-change="dictPageIndexChange($event)" @on-page-size-change="dictPageSizeChange($event)" v-if="dictPageShow" />
        <Button type="text" shape="circle" icon="md-refresh" @click="dictRefresh()"></Button>
      </Row>
      <Spin size="large" fix v-if="dictSpinShow"></Spin>
    </Card>
    <Modal width="60" v-model="dictEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑数据字典
        </p>
        <Form class="pro-margin-top-20" ref="dictForm" :model="dict" :rules="dictFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="数据字典标识：" prop="dictKey">
                <Input v-model="dict.dictKey" placeholder="输入数据字典标识"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="数据字典名称：" prop="dictName">
                <Input v-model="dict.dictName" placeholder="输入数据字典名称"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="说明：" prop="notes">
                <Input v-model="dict.notes" placeholder="输入说明"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="排序权重：" prop="weightOrder">
                <InputNumber v-model="dict.weightOrder"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="版本号：" prop="versions">
                <InputNumber v-model="dict.versions"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="创建人账号：" prop="createId">
                <Input v-model="dict.createId" placeholder="输入创建人账号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="创建人名称：" prop="createName">
                <Input v-model="dict.createName" placeholder="输入创建人名称"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="创建时间：" prop="createTimes">
                <InputNumber v-model="dict.createTimes"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="修改人账号：" prop="modifyId">
                <Input v-model="dict.modifyId" placeholder="输入修改人账号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="修改人名称：" prop="modifyName">
                <Input v-model="dict.modifyName" placeholder="输入修改人名称"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="修改时间：" prop="modifyTimes">
                <InputNumber v-model="dict.modifyTimes"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem>
                <Button class="pro-margin-right-8" @click="toDictList()">取消</Button>
                <Button class="pro-margin-right-8" @click="resetDictForm()">重置</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="dictSubmitting" @click="saveDictForm()">保存</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="dictSubmitting" @click="saveAddDictForm()">继续录入</Button>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    </Modal>
    <Drawer width="45" v-model="dictDetailDrawerShow" :closable="false">
      <Card>
        <p slot="title">
          <Icon type="md-bulb"></Icon>
          数据字典详情
        </p>
        <Row>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据字典编号：{{ dict.dictId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据字典标识：{{ dict.dictKey }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据字典名称：{{ dict.dictName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>说明：{{ dict.notes }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>排序权重：{{ dict.weightOrder }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>版本号：{{ dict.versions }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>创建人账号：{{ dict.createId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>创建人名称：{{ dict.createName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>创建时间：{{ dict.createTimes }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>修改人账号：{{ dict.modifyId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>修改人名称：{{ dict.modifyName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>修改时间：{{ dict.modifyTimes }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import Dict from '@/view/cn/moonnow/dict/dict/Dict'
import DictQuery from '@/view/cn/moonnow/dict/dict/DictQuery'

export default {
  data () {
    return {
      dictColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '数据字典标识', key: 'dictKey', minWidth: 200 },
        { title: '数据字典名称', key: 'dictName', minWidth: 200 },
        { title: '说明', key: 'notes', minWidth: 200 },
        { title: '排序权重', key: 'weightOrder', minWidth: 200 },
        { title: '版本号', key: 'versions', minWidth: 200 },
        { title: '创建人账号', key: 'createId', minWidth: 200 },
        { title: '创建人名称', key: 'createName', minWidth: 200 },
        { title: '创建时间', key: 'createTimes', minWidth: 200 },
        { title: '修改人账号', key: 'modifyId', minWidth: 200 },
        { title: '修改人名称', key: 'modifyName', minWidth: 200 },
        { title: '修改时间', key: 'modifyTimes', minWidth: 200 }
      ],
      dictFormValidate: {
        dictKey: [
          { required: true, message: '数据字典标识不能为空', trigger: 'blur' }
        ],
        versions: [
          { type: 'number', required: true, message: '版本号不能为空', trigger: 'change' }
        ],
        createId: [
          { required: true, message: '创建人账号不能为空', trigger: 'blur' }
        ],
        createName: [
          { required: true, message: '创建人名称不能为空', trigger: 'blur' }
        ],
        createTimes: [
          { type: 'number', required: true, message: '创建时间不能为空', trigger: 'change' }
        ]
      },
      dictSet: [],
      dictCount: 0,
      dictRows: 10,
      dictPage: 1,
      dictSelectRow: [],
      dictSelectRowLength: 0,
      dict: new Dict(),
      dictQuery: new DictQuery(),
      dictSpinShow: false,
      dictPageShow: true,
      dictEditModalShow: false,
      dictSubmitting: false,
      dictContinue: false,
      dictDetailDrawerShow: false,
      dictQueryPoptipShow: false,
      dictQuerySubmitting: false,
      dictKeySearchSubmitting: false,
      dictKeySearchClickRate: 0
    }
  },
  computed: {
    ...mapState('dictStore', [
    ]),
    ...mapGetters('dictStore', [
    ])
  },
  methods: {
    ...mapMutations('dictStore', [
    ]),
    ...mapActions('dictStore', [
      'saveDict',
      'updateDict',
      'batchRemoveDict',
      'getDictByPk',
      'queryDict',
      'pagingQueryDict'
    ]),
    addDict () {
      this.dictEditModalShow = true
      this.dict = new Dict()
    },
    upDict () {
      this.dictEditModalShow = true
      let dictEdit = new Dict()
      dictEdit.dictId = this.dictSelectRow[0].dictId
      this.getDictByPk(dictEdit).then(res => {
        if (res.data.success) {
          Object.assign(this.dict, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toDictList () {
      this.dictEditModalShow = false
      this.resetDictForm()
      this.getDictSet()
    },
    resetDictForm () {
      this.$refs.dictForm.resetFields()
    },
    saveDictForm () {
      this.dictContinue = false
      this.submitDictForm()
    },
    saveAddDictForm () {
      this.dictContinue = true
      this.submitDictForm()
    },
    submitDictForm () {
      this.dictSubmitting = true
      this.$refs.dictForm.validate((valid) => {
        if (valid) {
          if (this.dict.dictId) {
            this.updateDict(this.dict).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '数据字典信息修改成功.', duration: 3 })
                this.resetDictForm()
                this.dict = new Dict()
                this.dictSubmitting = false
                if (!this.dictContinue) {
                  this.toDictList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.dictSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.dictSubmitting = false }, 3000)
            })
          } else {
            this.saveDict(this.dict).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '数据字典信息保存成功.', duration: 3 })
                this.resetDictForm()
                this.dict = new Dict()
                this.dictSubmitting = false
                if (!this.dictContinue) {
                  this.toDictList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.dictSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.dictSubmitting = false }, 3000)
            })
          }
        } else {
          setTimeout(() => { this.dictSubmitting = false }, 3000)
        }
      })
    },
    delDict () {
      this.dictSpinShow = true
      this.batchRemoveDict(this.dictSelectRow).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '数据字典信息删除成功.', duration: 3 })
          this.setDictSelectRow([])
          this.getDictSet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.dictSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.dictSpinShow = false }, 700)
      })
    },
    detailDict () {
      this.dictDetailDrawerShow = true
      let dictDetail = new Dict()
      dictDetail.dictId = this.dictSelectRow[0].dictId
      this.getDictByPk(dictDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.dict, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    dblclickDetailDict (rowData) {
      this.dictDetailDrawerShow = true
      let dictDetail = new Dict()
      dictDetail.dictId = rowData.dictId
      this.getDictByPk(dictDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.dict, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getDictSet () {
      this.dictSpinShow = true
      this.dictPageShow = true
      this.pagingQueryDict({ dictQuery: {}, rows: this.dictRows, page: this.dictPage }).then(res => {
        if (res.data.success) {
          this.dictSet = res.data.data
          this.dictCount = res.data.count
          this.setDictSelectRow([])
          setTimeout(() => { this.dictSpinShow = false }, 700)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.dictSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.dictSpinShow = false }, 700)
      })
    },
    keySearchDict (keyLikeValue) {
      if (keyLikeValue !== '') {
        this.dictKeySearchSubmitting = true
        this.dictPageShow = false
        this.dictSpinShow = true
        let dictQueryKeySearch = new DictQuery()
        dictQueryKeySearch.dictKeyAndKeyLike = '%' + keyLikeValue + '%'
        dictQueryKeySearch.dictNameOrKeyLike = '%' + keyLikeValue + '%'
        dictQueryKeySearch.notesOrKeyLike = '%' + keyLikeValue + '%'
        dictQueryKeySearch.createIdOrKeyLike = '%' + keyLikeValue + '%'
        dictQueryKeySearch.createNameOrKeyLike = '%' + keyLikeValue + '%'
        dictQueryKeySearch.modifyIdOrKeyLike = '%' + keyLikeValue + '%'
        dictQueryKeySearch.modifyNameOrKeyLike = '%' + keyLikeValue + '%'
        this.queryDict(dictQueryKeySearch).then(res => {
          if (res.data.success) {
            this.dictSet = res.data.data
            this.dictCount = res.data.count
            this.setDictSelectRow([])
            setTimeout(() => { this.dictSpinShow = false }, 700)
            setTimeout(() => { this.dictKeySearchSubmitting = false }, 5000)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.dictSpinShow = false }, 700)
            setTimeout(() => { this.dictKeySearchSubmitting = false }, 5000)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.dictSpinShow = false }, 700)
          setTimeout(() => { this.dictKeySearchSubmitting = false }, 5000)
        })
      } else {
        this.dictKeySearchClickRate++
        if (this.dictKeySearchClickRate > 10) {
          this.$Message.warning({ content: '兄dei，你是闲的吗？', duration: 6 })
          this.dictKeySearchClickRate = 0
        }
      }
    },
    vagueQueryDict () {
      this.dictQuerySubmitting = true
      this.dictPageShow = false
      this.dictSpinShow = true
      this.dictQueryPoptipShow = false
      this.queryDict(this.dictQuery).then(res => {
        if (res.data.success) {
          this.dictSet = res.data.data
          this.dictCount = res.data.count
          this.setDictSelectRow([])
          setTimeout(() => { this.dictSpinShow = false }, 700)
          setTimeout(() => { this.dictQuerySubmitting = false }, 5000)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.dictSpinShow = false }, 700)
          setTimeout(() => { this.dictQuerySubmitting = false }, 5000)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.dictSpinShow = false }, 700)
        setTimeout(() => { this.dictQuerySubmitting = false }, 5000)
      })
    },
    resetDictQueryForm () {
      this.$refs.dictQueryForm.resetFields()
    },
    setDictSelectRow (list) {
      this.dictSelectRow = list
      this.dictSelectRowLength = list.length
    },
    dictPageIndexChange (pageIndex) {
      this.dictPage = pageIndex
      this.getDictSet()
    },
    dictPageSizeChange (pageSize) {
      this.dictRows = pageSize
      this.dictPage = 1
      this.getDictSet()
    },
    dictRefresh () {
      this.dictPage = 1
      this.getDictSet()
    }
  },
  mounted () {
    this.getDictSet()
  }
}
</script>
