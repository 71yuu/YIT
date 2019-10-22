<template>
  <div>
    <div>
      <el-form ref="formData" :model="formData" label-width="150px">
        <el-alert class="title" :closable="false" title="站点信息" type="info" />
          <br/>
          <el-form-item label="站点标题:">
            <el-input style="width:400px;" v-model="formData.websiteTitle" placeholder="请输入站点标题"></el-input>
          </el-form-item>
          <el-form-item label="站点关键词:">
            <el-input style="width:400px;" v-model="formData.websiteKeyword" placeholder="请输入站点关键词" ></el-input>
          </el-form-item>
          <el-form-item label="站点描述:">
            <el-input style="width:400px;" type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入站点描述" v-model="formData.websiteDesc"></el-input>
          </el-form-item>
          <el-form-item label="站点版权:">
            <el-input style="width:400px;" v-model="formData.copyright" placeholder="请输入站点版权"></el-input>
          </el-form-item>
          <el-form-item label="个人微信:" prop="advImg">
            <div>
              <el-upload
                class="avatar-uploader"
                action=""
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :http-request="handlePost">
                <img v-if="formData.weixin" :src="formData.weixin" class="avatar" alt="" height="200px" width="200px">
                <i v-else  class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </div>
          </el-form-item>
          <el-form-item label="个人博客地址:">
            <el-input style="width:400px;" v-model="formData.blog" placeholder="个人博客地址"></el-input>
          </el-form-item>
          <el-form-item label="GitHub地址:">
            <el-input style="width:400px;" v-model="formData.github" placeholder="GitHub地址"></el-input>
          </el-form-item>
          <el-form-item label="用户协议:" style="width:80%">
             <div id="userAgreement" style="height:400px;max-height:500px;"></div>
          </el-form-item>
        <el-alert class="title" :closable="false" title="讲师招募" type="info" />
          <br/>
          <el-form-item label="招募信息:" style="width:80%">
             <div id="recruitInfo" style="height:400px;max-height:500px;"></div>
          </el-form-item>
          <el-form-item label="入驻协议:" style="width:80%">
             <div id="entryAgreement" style="height:400px;max-height:500px;"></div>
          </el-form-item>
        <br/>
      </el-form>
    </div>
      <el-row style="margin-top:17px; ">
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确 定</el-button>
      </el-row>
      <br/>
      <br/>
  </div>
</template>
<script>
  import * as api from '@/api/sys'
  import * as commonalityApi from '@/api/commonality'
  export default {
    data() {
      return {
        formData: {}
      }
    },
    mounted() {
      this.loading.show()
      this.getWebsite()
      this.createEdit();
      this.loading.hide()
    },
    methods: {
        handleChange(value) {
            this.formData.sort = value
        },
        handleAvatarSuccess(res, file) {
            console.log(file)
            console.log(res)
            this.formData.weixin = URL.createObjectURL(file.raw);
        },
        handlePost(file) {
            console.log(file);
            const fd = new FormData();
            fd.append('picFile', file.file)
            // 配置post请求的参数。参数名file,后面跟要传的文件，参数名fileType，值为category（看后端的具体要求）
            commonalityApi.uploadPic(fd).then(response => {
                this.formData.weixin = response.data
                console.log(this.advImg);
            }).catch(() => {
                this.$message({
                    showClose: true,
                    message: '上传失败',
                    type: 'error'
                });
            })
        },
      createEdit() {
        const E = require('wangeditor')
        this.editor = new E('#userAgreement')
        this.editor1 = new E('#recruitInfo')
        this.editor2 = new E('#entryAgreement')
        if (this.formData !== undefined) {
          setTimeout(() => {
          this.editor.create(); // 创建用户协议富文本编辑器
          this.editor.customConfig.customUploadImg = this.editorUpload
          if (this.formData.userAgreement !== undefined && this.formData.userAgreement !== '' && this.formData.userAgreement !== null) {
            this.editor.txt.html(this.formData.userAgreement)
          } else {
            this.editor.txt.html('')
          }
          this.editor1.create(); // 创建招募信息富文本编辑器
          this.editor1.customConfig.customUploadImg = this.editorUpload
          if (this.formData.recruitInfo !== undefined && this.formData.recruitInfo !== '' && this.formData.recruitInfo !== null) {
            this.editor1.txt.html(this.formData.recruitInfo)
          } else {
            this.editor1.txt.html('')
          }
          this.editor2.create(); // 创建入驻协议富文本编辑器
          this.editor2.customConfig.customUploadImg = this.editorUpload
          if (this.formData.entryAgreement !== undefined && this.formData.entryAgreement !== '' && this.formData.entryAgreement !== null) {
            this.editor2.txt.html(this.formData.entryAgreement)
          } else {
            this.editor2.txt.html('')
          }
        }, 100)
        }
      },
      getWebsite() {
        api.websiteView({}).then(res => {
        console.log(res)
        if (res.code === 200) {
          this.formData = res.data
        }
      }).catch(() => {
        this.loading.hide()
      })
      },
      // 编辑器上传图片
      editorUpload(files, insert) {
        const file = files[0];
        const param = new FormData();
        param.append('picFile', file, file.name);
        commonalityApi.uploadPic(param).then(res => {
          if (res.code === 200) {
            const imgUrl = res.data
            insert(imgUrl)
          }
        }).catch(() => {
          this.$message({
            type: 'error',
            message: '上传图片出错，请稍后重试'
          })
        })
      },
      submitForm(formData) {
        this.formData.userAgreement = this.editor.txt.html() // 获取富文本编辑器用户协议内容
        this.formData.recruitInfo = this.editor1.txt.html() // 获取富文本编辑器招募信息内容
        this.formData.entryAgreement = this.editor2.txt.html() // 获取富文本编辑器入驻协议内容
        this.$refs[formData].validate((valid) => {
          if (valid) {
            this.loading.show()
            api.websiteUpdate(this.formData).then(res => {
              this.loading.hide()
              if (res.code === 200 && res.data > 0) {
                this.tips('操作成功', 'success')
                this.handleClose()
              } else {
                this.$message({
                  type: 'error',
                  message: "更新失败"
                });
              }
            }).catch(() => {
              this.loading.hide()
            })
          } else {
            this.$message({
              type: 'error',
              message: "更新失败"
            });
          }
        })
      },
      handleClose() {
        const view = {
          key: this.$route.fullPath
        }
        this.$store.dispatch('delView', view).then(({ visitedViews }) => {
          // console.log(visitedViews)
          const latestView = visitedViews.slice(-1)[0]
          if (latestView) {
            this.$router.push(latestView.route.fullPath)
          } else {
            this.$router.push('/')
          }
        })
      }
    }
  }
</script>
