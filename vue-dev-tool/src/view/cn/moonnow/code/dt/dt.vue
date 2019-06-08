<style lang="less" scoped>
  @import './dt.less';
</style>

<template>
  <div>
    <Card>
      <Button class="pro-list-button" type="primary" ghost icon="ios-add-circle-outline" @click="add()">新建</Button>
    </Card>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import Dt from '@/view/cn/moonnow/code/dt/dt'

export default {
  props: {
  },
  data () {
    return {
    }
  },
  computed: {
    ...mapState('dtStore', [
    ]),
    ...mapGetters('dtStore', [
    ])
  },
  methods: {
    ...mapMutations('dtStore', [
    ]),
    ...mapActions('dtStore', [
      'saveDt'
    ]),
    add () {
      let dt = new Dt()
      dt.dtSql = 'aaa'
      dt.dtName = 'aaa'
      this.saveDt(dt).then(res => {
        if (res.data.success) {
          alert(res.data.msg)
        } else {
          alert(res.data.msg)
        }
      })
    }
  },
  mounted () {
  }
}
</script>
