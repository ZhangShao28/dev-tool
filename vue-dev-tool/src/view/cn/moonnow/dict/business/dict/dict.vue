<style lang="less" scoped>
  @import './dict.less';
</style>

<template>
  <div class="demo-split">
    <Split v-model="split">
      <div slot="left">
        <Card>
          <Row>
            <Col :xs="16" :sm="14" :md="10" :lg="10">
              <Input placeholder="关键字搜索" search enter-button :disabled="dictKeySearchSubmitting" @on-search="keySearchDict($event)" />
            </Col>
            <Col :xs="8" :sm="10" :md="14" :lg="14">
              <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="350" v-model="dictQueryPoptipShow">
                <Button icon="ios-locate-outline">精准查询</Button>
                <div slot="content">
                  <Form class="pro-margin-top-10" ref="dictQueryForm" :model="dictQuery" :label-width="120">
                    <Row>
                      <Col span="24">
                        <FormItem label="数据字典名称：" prop="dictName">
                          <Input v-model="dictQuery.dictName" placeholder="输入数据字典名称"></Input>
                        </FormItem>
                      </Col>
                      <Col span="24">
                        <FormItem label="数据字典标识：" prop="dictKey">
                          <Input v-model="dictQuery.dictKey" placeholder="输入数据字典标识"></Input>
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
          <Table :columns="dictColumns" :data="dictSet" :highlight-row="true" @on-selection-change="setDictSelectRow($event)" @on-row-dblclick="dblclickDetailDict($event)" @on-row-click="clickDictRow($event)"></Table>
          <Row class="pro-margin-top-10" type="flex" justify="center">
            <Page size="small" placement="top" show-total show-sizer show-elevator :total="dictCount" :current="dictPage" :page-size-opts="[10, 30, 50]" @on-change="dictPageIndexChange($event)" @on-page-size-change="dictPageSizeChange($event)" v-if="dictPageShow" />
            <Button type="text" shape="circle" icon="md-refresh" @click="dictRefresh()"></Button>
          </Row>
          <Spin size="large" fix v-if="dictSpinShow"></Spin>
        </Card>
      </div>
      <div slot="right">
        <Card>
          <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addDictValue()">新建</Button>
          <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upDictValue()" v-if="dictValueSelectRowLength === 1">编辑</Button>
          <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delDictValue()" v-if="dictValueSelectRowLength > 0">
            <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
          </Poptip>
          <Button class="pro-list-button" icon="md-bulb" @click="detailDictValue()" v-if="dictValueSelectRowLength === 1">详情</Button>
          <Table :columns="dictValueColumns" :data="dictValueSet" @on-selection-change="setDictValueSelectRow($event)" @on-row-dblclick="dblclickDetailDictValue($event)"></Table>
          <Row class="pro-margin-top-10" type="flex" justify="center">
            <Button type="text" shape="circle" icon="md-refresh" @click="dictValueRefresh()"></Button>
          </Row>
          <Spin size="large" fix v-if="dictValueSpinShow"></Spin>
        </Card>
      </div>
    </Split>
    <Modal width="60" v-model="dictEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑数据字典
        </p>
        <Form class="pro-margin-top-20" ref="dictForm" :model="dict" :rules="dictFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="数据字典名称：" prop="dictName">
                <Input v-model="dict.dictName" placeholder="输入数据字典名称"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="数据字典标识：" prop="dictKey">
                <Input v-model="dict.dictKey" placeholder="输入数据字典标识"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="说明：" prop="notes">
                <Input v-model="dict.notes" type="textarea" :rows="3" placeholder="输入说明"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="排序权重：" prop="weightOrder">
                <InputNumber v-model="dict.weightOrder" :min="1"></InputNumber>
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
          <Col :xs="24" :sm="24" :md="24" :lg="12">
            <p>数据字典名称：{{ dict.dictName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="12">
            <p>数据字典标识：{{ dict.dictKey }}</p>
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
        </Row>
      </Card>
    </Drawer>
    <Modal width="60" v-model="dictValueEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑数据字典值
        </p>
        <Form class="pro-margin-top-20" ref="dictValueForm" :model="dictValue" :rules="dictValueFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="所属数据字典：" prop="dictId">
                <Select v-model="dictValue.dictId" clearable filterable>
                  <Option v-for="item in dictListOfDictValueEdit" :value="item.dictId" :key="item.dictId">{{ item.dictName + ' [ ' + item.dictKey + ' ]' }}</Option>
                </Select>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="代码：" prop="code">
                <Input v-model="dictValue.code" placeholder="输入代码"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="显示名称：" prop="displayName">
                <Input v-model="dictValue.displayName" placeholder="输入显示名称"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="排序权重：" prop="weightOrder">
                <InputNumber v-model="dictValue.weightOrder" :min="1"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="描述：" prop="description">
                <Input v-model="dictValue.description" type="textarea" :rows="3" placeholder="输入描述"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem>
                <Button class="pro-margin-right-8" @click="toDictValueList()">取消</Button>
                <Button class="pro-margin-right-8" @click="resetDictValueForm()">重置</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="dictValueSubmitting" @click="saveDictValueForm()">保存</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="dictValueSubmitting" @click="saveAddDictValueForm()">继续录入</Button>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    </Modal>
    <Drawer width="45" v-model="dictValueDetailDrawerShow" :closable="false">
      <Card>
        <p slot="title">
          <Icon type="md-bulb"></Icon>
          数据字典值详情
        </p>
        <Row>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据字典值编号：{{ dictValue.dictValueId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据字典编号：{{ dictValue.dictId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="12">
            <p>代码：{{ dictValue.code }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="12">
            <p>显示名称：{{ dictValue.displayName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>排序权重：{{ dictValue.weightOrder }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>描述：{{ dictValue.description }}</p>
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
import DictValue from '@/view/cn/moonnow/dict/dictValue/DictValue'
import DictValueQuery from '@/view/cn/moonnow/dict/dictValue/DictValueQuery'

export default {
  data () {
    return {
      split: 0.5,
      currentDictIdOfClickDict: null,
      dictListOfDictValueEdit: [],
      dictColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '数据字典名称', key: 'dictName', minWidth: 200 },
        { title: '数据字典标识', key: 'dictKey', minWidth: 200 },
        { title: '排序权重', key: 'weightOrder', width: 100 }
      ],
      dictFormValidate: {
        dictKey: [
          { required: true, message: '数据字典标识不能为空', trigger: 'blur' }
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
      dictKeySearchClickRate: 0,
      dictValueColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '代码', key: 'code', minWidth: 200 },
        { title: '显示名称', key: 'displayName', minWidth: 200 },
        { title: '排序权重', key: 'weightOrder', width: 100 }
      ],
      dictValueFormValidate: {
        code: [
          { required: true, message: '代码不能为空', trigger: 'blur' }
        ],
        displayName: [
          { required: true, message: '显示名称不能为空', trigger: 'blur' }
        ],
        dictId: [
          { required: true, message: '数据字典编号不能为空', trigger: 'blur' }
        ]
      },
      dictValueSet: [],
      dictValueSelectRow: [],
      dictValueSelectRowLength: 0,
      dictValue: new DictValue(),
      dictValueQuery: new DictValueQuery(),
      dictValueSpinShow: false,
      dictValueEditModalShow: false,
      dictValueSubmitting: false,
      dictValueContinue: false,
      dictValueDetailDrawerShow: false
    }
  },
  computed: {
    ...mapState('dictStore', [
    ]),
    ...mapGetters('dictStore', [
    ]),
    ...mapState('dictValueStore', [
    ]),
    ...mapGetters('dictValueStore', [
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
      'getAllDict',
      'queryDict',
      'pagingQueryDict'
    ]),
    ...mapMutations('dictValueStore', [
    ]),
    ...mapActions('dictValueStore', [
      'saveDictValue',
      'updateDictValue',
      'batchRemoveDictValue',
      'getDictValueByPk',
      'queryDictValue'
    ]),
    clickDictRow (rowData) {
      this.currentDictIdOfClickDict = rowData.dictId
      this.dictValueSpinShow = true
      let dictValueQuery = new DictValueQuery()
      dictValueQuery.dictId = this.currentDictIdOfClickDict
      this.queryDictValue(dictValueQuery).then(res => {
        if (res.data.success) {
          this.dictValueSet = res.data.data
          this.setDictValueSelectRow([])
          setTimeout(() => { this.dictValueSpinShow = false }, 700)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.dictValueSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.dictValueSpinShow = false }, 700)
      })
    },
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
    },
    addDictValue () {
      if (this.currentDictIdOfClickDict) {
        this.dictValueEditModalShow = true
        this.dictValue = new DictValue()
        this.dictValue.dictId = this.currentDictIdOfClickDict
        this.getAllDict().then(res => {
          if (res.data.success) {
            this.dictListOfDictValueEdit = res.data.data
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
        })
      } else {
        this.dictValueEditModalShow = true
        this.dictValue = new DictValue()
        this.getAllDict().then(res => {
          if (res.data.success) {
            this.dictListOfDictValueEdit = res.data.data
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
        })
      }
    },
    upDictValue () {
      this.dictValueEditModalShow = true
      let dictValueEdit = new DictValue()
      dictValueEdit.dictValueId = this.dictValueSelectRow[0].dictValueId
      this.getDictValueByPk(dictValueEdit).then(res => {
        if (res.data.success) {
          Object.assign(this.dictValue, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
      this.getAllDict().then(res => {
        if (res.data.success) {
          this.dictListOfDictValueEdit = res.data.data
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toDictValueList () {
      this.dictValueEditModalShow = false
      this.resetDictValueForm()
      this.getDictValueSet()
    },
    resetDictValueForm () {
      this.$refs.dictValueForm.resetFields()
    },
    saveDictValueForm () {
      this.dictValueContinue = false
      this.submitDictValueForm()
    },
    saveAddDictValueForm () {
      this.dictValueContinue = true
      this.submitDictValueForm()
    },
    submitDictValueForm () {
      this.dictValueSubmitting = true
      this.$refs.dictValueForm.validate((valid) => {
        if (valid) {
          if (this.dictValue.dictValueId) {
            this.updateDictValue(this.dictValue).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '数据字典值信息修改成功.', duration: 3 })
                this.resetDictValueForm()
                this.dictValue = new DictValue()
                this.dictValueSubmitting = false
                if (!this.dictValueContinue) {
                  this.toDictValueList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.dictValueSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.dictValueSubmitting = false }, 3000)
            })
          } else {
            this.saveDictValue(this.dictValue).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '数据字典值信息保存成功.', duration: 3 })
                this.resetDictValueForm()
                this.dictValue = new DictValue()
                this.dictValueSubmitting = false
                if (!this.dictValueContinue) {
                  this.toDictValueList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.dictValueSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.dictValueSubmitting = false }, 3000)
            })
          }
        } else {
          setTimeout(() => { this.dictValueSubmitting = false }, 3000)
        }
      })
    },
    delDictValue () {
      this.dictValueSpinShow = true
      this.batchRemoveDictValue(this.dictValueSelectRow).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '数据字典值信息删除成功.', duration: 3 })
          this.setDictValueSelectRow([])
          this.getDictValueSet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.dictValueSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.dictValueSpinShow = false }, 700)
      })
    },
    detailDictValue () {
      this.dictValueDetailDrawerShow = true
      let dictValueDetail = new DictValue()
      dictValueDetail.dictValueId = this.dictValueSelectRow[0].dictValueId
      this.getDictValueByPk(dictValueDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.dictValue, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    dblclickDetailDictValue (rowData) {
      this.dictValueDetailDrawerShow = true
      let dictValueDetail = new DictValue()
      dictValueDetail.dictValueId = rowData.dictValueId
      this.getDictValueByPk(dictValueDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.dictValue, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getDictValueSet () {
      if (this.currentDictIdOfClickDict) {
        this.dictValueSpinShow = true
        let dictValueQuery = new DictValueQuery()
        dictValueQuery.dictId = this.currentDictIdOfClickDict
        this.queryDictValue(dictValueQuery).then(res => {
          if (res.data.success) {
            this.dictValueSet = res.data.data
            this.setDictValueSelectRow([])
            setTimeout(() => { this.dictValueSpinShow = false }, 700)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.dictValueSpinShow = false }, 700)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.dictValueSpinShow = false }, 700)
        })
      }
    },
    setDictValueSelectRow (list) {
      this.dictValueSelectRow = list
      this.dictValueSelectRowLength = list.length
    },
    dictValueRefresh () {
      this.dictValuePage = 1
      this.getDictValueSet()
    }
  },
  mounted () {
    this.getDictSet()
  }
}
</script>
