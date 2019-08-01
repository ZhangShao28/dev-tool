<style lang="less">
  @import './login.less';
</style>

<template>
  <div class="login">
    <!-- <div class="login-con">
      <Card icon="log-in" title="欢迎登录" :bordered="false">
        <div class="form-con">
          <login-form @on-success-valid="handleSubmit"></login-form>
          <p class="login-tip">输入任意用户名和密码即可</p>
        </div>
      </Card>
    </div> -->
    <Row class="pro-margin-top-100" type="flex" justify="center">
      <Col class="login-title" span="2">
        <img class="logo" src="../../assets/images/logo-color.svg" />
      </Col>
      <Col span="4">
        <span class="title">代码生成器</span>
      </Col>
    </Row>
    <Row class="pro-margin-top-25" type="flex" justify="center">
      <Col class="login-input" span="2">
        <a :class="{ 'login-tab': !accountOnShow, 'login-tab-on': accountOnShow }" @click="accountOn()">账户密码登录</a>
        <Divider class="under-line" v-if="accountUnderLineShow" />
      </Col>
      <Col class="login-input" span="2">
        <a :class="{ 'login-tab': !phoneOnShow, 'login-tab-on': phoneOnShow }" @click="phoneOn()">手机号登录</a>
        <Divider class="under-line" v-if="phoneUnderLineShow" />
      </Col>
    </Row>
    <Row type="flex" justify="center" v-if="accountLoginShow">
      <Col class="login-input" span="4">
        <Form class="pro-margin-top-12" ref="accountForm" :model="account" :rules="accountFormValidate" @keydown.enter.native="handleSubmit">
          <FormItem prop="account">
            <Input size="large" v-model="account.account" placeholder="账号">
              <Icon type="ios-contact-outline" slot="prefix"></Icon>
            </Input>
          </FormItem>
          <FormItem prop="password">
            <Input size="large" type="password" v-model="account.password" placeholder="密码" v-if="passwordShow">
              <Icon type="ios-lock-outline" slot="prefix"></Icon>
              <Icon :color="eyeIconColor" type="ios-eye-outline" slot="suffix" @click="showPassword()" @mouseenter.native="eyeIconMouseenter()" @mouseleave.native="eyeIconMouseleave()"></Icon>
            </Input>
            <Input size="large" v-model="account.password" placeholder="密码" v-if="!passwordShow">
              <Icon type="ios-lock-outline" slot="prefix"></Icon>
              <Icon :color="eyeIconColor" type="ios-eye-off-outline" slot="suffix" @click="hidePassword()" @mouseenter.native="eyeIconMouseenter()" @mouseleave.native="eyeIconMouseleave()"></Icon>
            </Input>
          </FormItem>
          <FormItem>
            <Button type="primary" long @click="xxx()">登录</Button>
          </FormItem>
          <a class="pro-float-left" @click="xxx()">忘记密码</a>
          <a class="pro-float-right" @click="xxx()">注册账号</a>
        </Form>
      </Col>
    </Row>
    <Row type="flex" justify="center" v-if="phoneLoginShow">
      <Col class="login-input" span="4">
        <Form class="pro-margin-top-12" ref="phoneForm" :model="phone" :rules="phoneFormValidate" @keydown.enter.native="handleSubmit">
          <FormItem prop="mobilePhoneNumber">
            <Input size="large" v-model="phone.mobilePhoneNumber" placeholder="手机号">
              <Icon type="ios-contact-outline" slot="prefix"></Icon>
            </Input>
          </FormItem>
          <FormItem>
            <Button type="primary" long @click="xxx()">登录</Button>
          </FormItem>
          <a class="pro-float-left" @click="xxx()">忘记密码</a>
          <a class="pro-float-right" @click="xxx()">注册账号</a>
        </Form>
      </Col>
    </Row>
  </div>
</template>

<script>
import LoginForm from '_c/login-form'
import { mapActions } from 'vuex'
import LoginInfoVO from '@/view/cn/moonnow/sign/business/login/LoginInfoVO'

export default {
  data () {
    return {
      accountUnderLineShow: true,
      phoneUnderLineShow: false,
      accountOnShow: true,
      phoneOnShow: false,
      account: new LoginInfoVO(),
      passwordShow: true,
      accountFormValidate: {
        account: [
          { required: true, message: '账号不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ]
      },
      eyeIconColor: '#515a6e',
      accountLoginShow: true,
      phoneLoginShow: false,
      phone: new LoginInfoVO(),
      phoneFormValidate: {
        mobilePhoneNumber: [
          { required: true, message: '手机号不能为空', trigger: 'blur' }
        ],
        verificationCode: [
          { required: true, message: '验证码不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  components: {
    LoginForm
  },
  methods: {
    ...mapActions([
      'handleLogin',
      'getUserInfo'
    ]),
    handleSubmit ({ userName, password }) {
      this.handleLogin({ userName, password }).then(res => {
        this.getUserInfo().then(res => {
          this.$router.push({
            name: this.$config.homeName
          })
        })
      })
    },
    accountOn () {
      this.accountUnderLineShow = true
      this.phoneUnderLineShow = false
      this.accountOnShow = true
      this.phoneOnShow = false
      this.accountLoginShow = true
      this.phoneLoginShow = false
      this.$refs.accountForm.resetFields()
    },
    phoneOn () {
      this.phoneUnderLineShow = true
      this.accountUnderLineShow = false
      this.phoneOnShow = true
      this.accountOnShow = false
      this.phoneLoginShow = true
      this.accountLoginShow = false
      this.$refs.phoneForm.resetFields()
    },
    showPassword () {
      this.passwordShow = false
    },
    hidePassword () {
      this.passwordShow = true
    },
    eyeIconMouseenter () {
      this.eyeIconColor = '#2d8cf0'
    },
    eyeIconMouseleave () {
      this.eyeIconColor = '#515a6e'
    }
  }
}
</script>

<style>

</style>
