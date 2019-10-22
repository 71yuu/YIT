<template>
  <!--弹窗-->
  <el-dialog
     width="30%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form :model="formData" :rules="rules" ref="formData" label-width="100px">
      <el-form-item label="导航标题：" prop="navTitle" width="200">
        <template>
          <el-input v-model="formData.navTitle"></el-input>
        </template>
      </el-form-item>
      <el-form-item label="导航地址：" prop="navUrl" width="200">
        <template>
          <el-input v-model="formData.navUrl"></el-input>
        </template>
      </el-form-item>
      <el-form-item label="打开方式：" prop="target" width="200">
        <template>
          <el-radio v-model="formData.target" label="_blank">新窗口打开</el-radio>
          <el-radio v-model="formData.target" label="_self">同窗口打开</el-radio>
        </template>
      </el-form-item>
      <el-form-item label="排序：" prop="sort" width="200">
        <template>
          <el-input-number style="width: 300px;" v-model="formData.sort" @change="handleChange" :min="1" :max="10000"></el-input-number>
        </template>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
      <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
      <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确定</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
  import * as api from '@/api/homepage'
  export default {
    name: 'Edit',
    data() {
      return {
        opts: {
          navList: []
        },
        ada: {},
        formData: {
          target: '_blank',
          sort: 1
        },
        rules: {
          navTitle: [
              { required: true, message: '请填写导航标题', trigger: 'blur' }
          ],
          navUrl: [
            { required: true, message: '请填写导肮地址', trigger: 'blur' }
          ],
          target: [
            { required: true, message: '请选择打开方式', trigger: 'blur' }
          ],
          sort: [
              { required: true, message: '请选择排序值', trigger: 'blur' }
          ]
        }
      }
    },
    props: {
      visible: {
        type: Boolean,
        default: false
      },
      title: {
        type: String,
        default: ''
      }
    },
    mounted() {
      this.$store.dispatch('GetOpts', { enumName: "NavEnum", type: 'arr' }).then(res => {
        this.opts.navList = res
      })
    },
    methods: {
      handleClose(done) {
        this.formData = { target: '_blank', sort: 1 }
        this.$refs['formData'].resetFields()
        this.$emit('close-callback')
      },
      handleChange(value) {
          this.formData.sort = value
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (!this.formData.navTitle) {
            this.$message({
              type: 'error',
              message: '请填写导航标题'
            });
            return false
          }
          if (!this.formData.navUrl) {
              this.$message({
                  type: 'error',
                  message: '请填写导航地址'
              })
              return false
          }
          if (!this.formData.target) {
            this.$message({
              type: 'error',
              message: '请选择打开方式'
            });
            return false
          }
          if (!this.formData.sort) {
              this.$message({
                  type: 'error',
                  message: '请选择排序值'
              })
          }
          if (valid) {
            //新增底部导航栏，给父ID赋值
            this.formData.parentId = 0
            this.loading.show()
            api.navBarSave(this.formData).then(res => {
              this.loading.hide()
              if (res.code === 200 && res.data > 0) {
                // 提交成功, 关闭窗口, 刷新列表
                this.tips('操作成功', 'success')
                this.handleClose()
              } else {
                this.$message({
                  type: 'error',
                  message: "提交失败"
                });
              }
            }).catch(() => {
              this.loading.hide()
            })
          } else {
            this.$message({
              type: 'error',
              message: "提交失败"
            });
          }
        });
      }
    }
  }
</script>
