<style lang="less" scoped>
  @import './coding.less';
</style>

<template>
  <div>
    <Card>
      <p slot="title">
        选择数据库表（一键生成只生成代码，不生成项目）
      </p>
      <Form :label-width="200">
        <Row class="pro-margin-top-20">
          <Col span="21">
            <FormItem label="数据库表：">
              <Select v-model="dtId" clearable filterable @on-change="changeDt($event)">
                <Option v-for="item in dtList" :value="item.dtId" :key="item.dtId">{{ item.dtName + ' [ ' + item.dtNameAnnotation + ' ]' }}</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingAll()">一键生成</Button>
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Card>
    <Card>
      <p slot="title">
        生成项目（项目模块只生成一次就行）
      </p>
      <Form :label-width="200">
        <Row class="pro-margin-top-20">
          <Col span="21">
            <FormItem label="项目路径：">
              <Input v-model="configVo.projectPath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingProject()">生成项目</Button>
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Card>
    <Card>
      <p slot="title">
        生成代码
      </p>
      <Form :label-width="200">
        <Row class="pro-margin-top-20">
          <Col span="21">
            <FormItem label="java实体类文件路径：">
              <Input v-model="configVo.jdbcEntityFilePath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingJdbcEntity()">生成代码</Button>
            </FormItem>
          </Col>
          <Col span="21">
            <FormItem label="java查询类文件路径：">
              <Input v-model="configVo.queryFilePath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingQuery()">生成代码</Button>
            </FormItem>
          </Col>
          <Col span="21">
            <FormItem label="java展示类文件路径：">
              <Input v-model="configVo.voFilePath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingVo()">生成代码</Button>
            </FormItem>
          </Col>
          <Col span="21">
            <FormItem label="java持久化接口文件路径：">
              <Input v-model="configVo.persistentInterfaceFilePath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingIPersistent()">生成代码</Button>
            </FormItem>
          </Col>
          <Col span="21">
            <FormItem label="java持久化实现文件路径：">
              <Input v-model="configVo.jdbcPersistentImplFilePath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingJdbcPersistentImpl()">生成代码</Button>
            </FormItem>
          </Col>
          <Col span="21">
            <FormItem label="java服务接口文件路径：">
              <Input v-model="configVo.serviceInterfaceFilePath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingIService()">生成代码</Button>
            </FormItem>
          </Col>
          <Col span="21">
            <FormItem label="java服务实现文件路径：">
              <Input v-model="configVo.serviceImplFilePath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingServiceImpl()">生成代码</Button>
            </FormItem>
          </Col>
          <Col span="21">
            <FormItem label="java控制器文件路径：">
              <Input v-model="configVo.controllerFilePath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingController()">生成代码</Button>
            </FormItem>
          </Col>
          <Col span="21">
            <FormItem label="Vue请求服务文件路径：">
              <Input v-model="configVo.vueServiceFilePath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingVueService()">生成代码</Button>
            </FormItem>
          </Col>
          <Col span="21">
            <FormItem label="Vue数据仓库文件路径：">
              <Input v-model="configVo.vueStoreFilePath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingVueStore()">生成代码</Button>
            </FormItem>
          </Col>
          <Col span="21">
            <FormItem label="Vue实体类文件路径：">
              <Input v-model="configVo.vueEntityFilePath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingVueEntity()">生成代码</Button>
            </FormItem>
          </Col>
          <Col span="21">
            <FormItem label="Vue查询类文件路径：">
              <Input v-model="configVo.vueQueryFilePath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingVueQuery()">生成代码</Button>
            </FormItem>
          </Col>
          <Col span="21">
            <FormItem label="Vue展示类文件路径：">
              <Input v-model="configVo.vueVoFilePath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingVueVo()">生成代码</Button>
            </FormItem>
          </Col>
          <Col span="21">
            <FormItem label="Vue页面文件路径：">
              <Input v-model="configVo.vueViewFilePath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingVueView()">生成代码</Button>
            </FormItem>
          </Col>
          <Col span="21">
            <FormItem label="Vue页面样式文件路径：">
              <Input v-model="configVo.vueViewCssFilePath"></Input>
            </FormItem>
          </Col>
          <Col span="3">
            <FormItem :label-width="10">
              <Button type="info" icon="ios-flash-outline" ghost @click="toCodingVueViewCss()">生成代码</Button>
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Card>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import ConfigVO from '@/view/cn/moonnow/code/business/coding/ConfigVO'
import Dt from '@/view/cn/moonnow/code/dt/Dt'

export default {
  data () {
    return {
      dtList: [],
      configVo: new ConfigVO(),
      dtId: null
    }
  },
  computed: {
    ...mapState('codingStore', [
    ]),
    ...mapGetters('codingStore', [
    ])
  },
  methods: {
    ...mapMutations('codingStore', [
    ]),
    ...mapActions('codingStore', [
      'getConfig',
      'codingProject',
      'codingJdbcEntity',
      'codingQuery',
      'codingVo',
      'codingIPersistent',
      'codingJdbcPersistentImpl',
      'codingIService',
      'codingServiceImpl',
      'codingController',
      'codingVueService',
      'codingVueStore',
      'codingVueEntity',
      'codingVueQuery',
      'codingVueVo',
      'codingVueView',
      'codingVueViewCss',
      'codingAll'
    ]),
    ...mapActions('dtStore', [
      'getAllDt'
    ]),
    changeDt (value) {
      if (value) {
        let dt = new Dt()
        dt.dtId = value
        this.getConfig(dt).then(res => {
          if (res.data.success) {
            this.$Message.success({ content: '代码文件路径配置信息获取成功.', duration: 3 })
            Object.assign(this.configVo, res.data.data[0])
            this.configVo.dtId = value
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
        })
      }
    },
    toCodingAll () {
      this.codingAll(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '代码生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingProject () {
      this.codingProject(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '项目生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingJdbcEntity () {
      this.codingJdbcEntity(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: 'java实体类文件生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingQuery () {
      this.codingQuery(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: 'java查询类文件生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingVo () {
      this.codingVo(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: 'java展示类文件生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingIPersistent () {
      this.codingIPersistent(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: 'java持久化接口文件生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingJdbcPersistentImpl () {
      this.codingJdbcPersistentImpl(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: 'java持久化实现文件生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingIService () {
      this.codingIService(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: 'java服务接口文件生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingServiceImpl () {
      this.codingServiceImpl(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: 'java服务实现文件生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingController () {
      this.codingController(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: 'java控制器文件生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingVueService () {
      this.codingVueService(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: 'Vue请求服务文件生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingVueStore () {
      this.codingVueStore(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: 'Vue数据仓库文件生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingVueEntity () {
      this.codingVueEntity(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: 'Vue实体类文件生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingVueQuery () {
      this.codingVueQuery(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: 'Vue查询类文件生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingVueVo () {
      this.codingVueVo(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: 'Vue展示类文件生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingVueView () {
      this.codingVueView(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: 'Vue页面文件生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCodingVueViewCss () {
      this.codingVueViewCss(this.configVo).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: 'Vue页面样式文件生成成功.', duration: 3 })
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    }
  },
  mounted () {
    this.getAllDt().then(res => {
      if (res.data.success) {
        this.dtList = res.data.data
      } else {
        this.$Message.error({ content: res.data.msg, duration: 6 })
      }
    }).catch(result => {
      this.$Message.error({ content: result.response.statusText, duration: 6 })
    })
  }
}
</script>
