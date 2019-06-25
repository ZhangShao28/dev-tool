<style lang="less" scoped>
  @import './dictValue.less';
</style>

<template>
  <div>
    <Card>
      <Row>
        <Col :xs="16" :sm="14" :md="10" :lg="6">
          <Input placeholder="关键字搜索" search enter-button :disabled="dictValueKeySearchSubmitting" @on-search="keySearchDictValue($event)" />
        </Col>
        <Col :xs="8" :sm="10" :md="14" :lg="18">
          <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="900" v-model="dictValueQueryPoptipShow">
            <Button icon="ios-locate-outline">精准查询</Button>
            <div slot="content">
              <Form class="pro-margin-top-10" ref="dictValueQueryForm" :model="dictValueQuery" :label-width="120">
                <Row>
                  <Col span="8">
                    <FormItem label="代码：" prop="code">
                      <Input v-model="dictValueQuery.code" placeholder="输入代码"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="显示名称：" prop="displayName">
                      <Input v-model="dictValueQuery.displayName" placeholder="输入显示名称"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="排序权重：" prop="weightOrder">
                      <InputNumber v-model="dictValueQuery.weightOrder"></InputNumber>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="描述：" prop="description">
                      <Input v-model="dictValueQuery.description" placeholder="输入描述"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="数据字典编号：" prop="dictId">
                      <Input v-model="dictValueQuery.dictId" placeholder="输入数据字典编号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="版本号：" prop="versions">
                      <InputNumber v-model="dictValueQuery.versions"></InputNumber>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="创建人账号：" prop="createId">
                      <Input v-model="dictValueQuery.createId" placeholder="输入创建人账号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="创建人名称：" prop="createName">
                      <Input v-model="dictValueQuery.createName" placeholder="输入创建人名称"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="创建时间：" prop="createTimes">
                      <InputNumber v-model="dictValueQuery.createTimes"></InputNumber>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="修改人账号：" prop="modifyId">
                      <Input v-model="dictValueQuery.modifyId" placeholder="输入修改人账号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="修改人名称：" prop="modifyName">
                      <Input v-model="dictValueQuery.modifyName" placeholder="输入修改人名称"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="修改时间：" prop="modifyTimes">
                      <InputNumber v-model="dictValueQuery.modifyTimes"></InputNumber>
                    </FormItem>
                  </Col>
                </Row>
                <Divider />
                <Row>
                  <Col span="24">
                    <FormItem class="pro-float-right">
                      <Button type="primary" @click="vagueQueryDictValue()" :loading="dictValueQuerySubmitting">精准查询</Button>
                      <Button class="pro-margin-left-8" @click="resetDictValueQueryForm()">重置</Button>
                    </FormItem>
                  </Col>
                </Row>
              </Form>
            </div>
          </Poptip>
        </Col>
      </Row>
      <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addDictValue()">新建</Button>
      <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upDictValue()" v-if="dictValueSelectRowLength === 1">编辑</Button>
      <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delDictValue()" v-if="dictValueSelectRowLength > 0">
        <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
      </Poptip>
      <Button class="pro-list-button" icon="md-bulb" @click="detailDictValue()" v-if="dictValueSelectRowLength === 1">详情</Button>
      <Table :columns="dictValueColumns" :data="dictValueSet" @on-selection-change="setDictValueSelectRow($event)" @on-row-dblclick="dblclickDetailDictValue($event)"></Table>
      <Row class="pro-margin-top-10" type="flex" justify="center">
        <Page size="small" placement="top" show-total show-sizer show-elevator :total="dictValueCount" :current="dictValuePage" :page-size-opts="[10, 30, 50]" @on-change="dictValuePageIndexChange($event)" @on-page-size-change="dictValuePageSizeChange($event)" v-if="dictValuePageShow" />
        <Button type="text" shape="circle" icon="md-refresh" @click="dictValueRefresh()"></Button>
      </Row>
      <Spin size="large" fix v-if="dictValueSpinShow"></Spin>
    </Card>
    <Modal width="60" v-model="dictValueEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑数据字典值
        </p>
        <Form class="pro-margin-top-20" ref="dictValueForm" :model="dictValue" :rules="dictValueFormValidate" :label-width="120">
          <Row type="flex" justify="center">
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
                <InputNumber v-model="dictValue.weightOrder"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="描述：" prop="description">
                <Input v-model="dictValue.description" placeholder="输入描述"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="数据字典编号：" prop="dictId">
                <Input v-model="dictValue.dictId" placeholder="输入数据字典编号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="版本号：" prop="versions">
                <InputNumber v-model="dictValue.versions"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="创建人账号：" prop="createId">
                <Input v-model="dictValue.createId" placeholder="输入创建人账号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="创建人名称：" prop="createName">
                <Input v-model="dictValue.createName" placeholder="输入创建人名称"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="创建时间：" prop="createTimes">
                <InputNumber v-model="dictValue.createTimes"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="修改人账号：" prop="modifyId">
                <Input v-model="dictValue.modifyId" placeholder="输入修改人账号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="修改人名称：" prop="modifyName">
                <Input v-model="dictValue.modifyName" placeholder="输入修改人名称"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="修改时间：" prop="modifyTimes">
                <InputNumber v-model="dictValue.modifyTimes"></InputNumber>
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
            <p>代码：{{ dictValue.code }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
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
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>数据字典编号：{{ dictValue.dictId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>版本号：{{ dictValue.versions }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>创建人账号：{{ dictValue.createId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>创建人名称：{{ dictValue.createName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>创建时间：{{ dictValue.createTimes }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>修改人账号：{{ dictValue.modifyId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>修改人名称：{{ dictValue.modifyName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>修改时间：{{ dictValue.modifyTimes }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import DictValue from '@/view/cn/moonnow/dict/dictValue/DictValue'
import DictValueQuery from '@/view/cn/moonnow/dict/dictValue/DictValueQuery'

export default {
  data () {
    return {
      dictValueColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '代码', key: 'code', minWidth: 200 },
        { title: '显示名称', key: 'displayName', minWidth: 200 },
        { title: '排序权重', key: 'weightOrder', minWidth: 200 },
        { title: '描述', key: 'description', minWidth: 200 },
        { title: '数据字典编号', key: 'dictId', minWidth: 200 },
        { title: '版本号', key: 'versions', minWidth: 200 },
        { title: '创建人账号', key: 'createId', minWidth: 200 },
        { title: '创建人名称', key: 'createName', minWidth: 200 },
        { title: '创建时间', key: 'createTimes', minWidth: 200 },
        { title: '修改人账号', key: 'modifyId', minWidth: 200 },
        { title: '修改人名称', key: 'modifyName', minWidth: 200 },
        { title: '修改时间', key: 'modifyTimes', minWidth: 200 }
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
      dictValueSet: [],
      dictValueCount: 0,
      dictValueRows: 10,
      dictValuePage: 1,
      dictValueSelectRow: [],
      dictValueSelectRowLength: 0,
      dictValue: new DictValue(),
      dictValueQuery: new DictValueQuery(),
      dictValueSpinShow: false,
      dictValuePageShow: true,
      dictValueEditModalShow: false,
      dictValueSubmitting: false,
      dictValueContinue: false,
      dictValueDetailDrawerShow: false,
      dictValueQueryPoptipShow: false,
      dictValueQuerySubmitting: false,
      dictValueKeySearchSubmitting: false,
      dictValueKeySearchClickRate: 0
    }
  },
  computed: {
    ...mapState('dictValueStore', [
    ]),
    ...mapGetters('dictValueStore', [
    ])
  },
  methods: {
    ...mapMutations('dictValueStore', [
    ]),
    ...mapActions('dictValueStore', [
      'saveDictValue',
      'updateDictValue',
      'batchRemoveDictValue',
      'getDictValueByPk',
      'queryDictValue',
      'pagingQueryDictValue'
    ]),
    addDictValue () {
      this.dictValueEditModalShow = true
      this.dictValue = new DictValue()
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
      this.dictValueSpinShow = true
      this.dictValuePageShow = true
      this.pagingQueryDictValue({ dictValueQuery: {}, rows: this.dictValueRows, page: this.dictValuePage }).then(res => {
        if (res.data.success) {
          this.dictValueSet = res.data.data
          this.dictValueCount = res.data.count
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
    keySearchDictValue (keyLikeValue) {
      if (keyLikeValue !== '') {
        this.dictValueKeySearchSubmitting = true
        this.dictValuePageShow = false
        this.dictValueSpinShow = true
        let dictValueQueryKeySearch = new DictValueQuery()
        dictValueQueryKeySearch.codeAndKeyLike = '%' + keyLikeValue + '%'
        dictValueQueryKeySearch.displayNameOrKeyLike = '%' + keyLikeValue + '%'
        dictValueQueryKeySearch.descriptionOrKeyLike = '%' + keyLikeValue + '%'
        dictValueQueryKeySearch.dictIdOrKeyLike = '%' + keyLikeValue + '%'
        dictValueQueryKeySearch.createIdOrKeyLike = '%' + keyLikeValue + '%'
        dictValueQueryKeySearch.createNameOrKeyLike = '%' + keyLikeValue + '%'
        dictValueQueryKeySearch.modifyIdOrKeyLike = '%' + keyLikeValue + '%'
        dictValueQueryKeySearch.modifyNameOrKeyLike = '%' + keyLikeValue + '%'
        this.queryDictValue(dictValueQueryKeySearch).then(res => {
          if (res.data.success) {
            this.dictValueSet = res.data.data
            this.dictValueCount = res.data.count
            this.setDictValueSelectRow([])
            setTimeout(() => { this.dictValueSpinShow = false }, 700)
            setTimeout(() => { this.dictValueKeySearchSubmitting = false }, 5000)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.dictValueSpinShow = false }, 700)
            setTimeout(() => { this.dictValueKeySearchSubmitting = false }, 5000)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.dictValueSpinShow = false }, 700)
          setTimeout(() => { this.dictValueKeySearchSubmitting = false }, 5000)
        })
      } else {
        this.dictValueKeySearchClickRate++
        if (this.dictValueKeySearchClickRate > 10) {
          this.$Message.warning({ content: '兄dei，你是闲的吗？', duration: 6 })
          this.dictValueKeySearchClickRate = 0
        }
      }
    },
    vagueQueryDictValue () {
      this.dictValueQuerySubmitting = true
      this.dictValuePageShow = false
      this.dictValueSpinShow = true
      this.dictValueQueryPoptipShow = false
      this.queryDictValue(this.dictValueQuery).then(res => {
        if (res.data.success) {
          this.dictValueSet = res.data.data
          this.dictValueCount = res.data.count
          this.setDictValueSelectRow([])
          setTimeout(() => { this.dictValueSpinShow = false }, 700)
          setTimeout(() => { this.dictValueQuerySubmitting = false }, 5000)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.dictValueSpinShow = false }, 700)
          setTimeout(() => { this.dictValueQuerySubmitting = false }, 5000)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.dictValueSpinShow = false }, 700)
        setTimeout(() => { this.dictValueQuerySubmitting = false }, 5000)
      })
    },
    resetDictValueQueryForm () {
      this.$refs.dictValueQueryForm.resetFields()
    },
    setDictValueSelectRow (list) {
      this.dictValueSelectRow = list
      this.dictValueSelectRowLength = list.length
    },
    dictValuePageIndexChange (pageIndex) {
      this.dictValuePage = pageIndex
      this.getDictValueSet()
    },
    dictValuePageSizeChange (pageSize) {
      this.dictValueRows = pageSize
      this.dictValuePage = 1
      this.getDictValueSet()
    },
    dictValueRefresh () {
      this.dictValuePage = 1
      this.getDictValueSet()
    }
  },
  mounted () {
    this.getDictValueSet()
  }
}
</script>
