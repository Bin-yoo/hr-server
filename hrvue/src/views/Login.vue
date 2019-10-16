<template>
    <div>
        <Form
            ref="formInline"
            :model="hr"
            class="login-container"
        >
            <h2 class="login_title">人事系统登录</h2>
            <FormItem prop="user">
                <Input
                    type="text"
                    v-model="hr.username"
                    placeholder="用户名"
                    @on-enter="Login"
                >
                    <Icon
                        type="ios-person-outline"
                        slot="prepend"
                    ></Icon>
                </Input>
            </FormItem>
            <FormItem prop="password">
                <Input
                    type="password"
                    v-model="hr.password"
                    placeholder="密码"
                    @on-enter="Login"
                >
                    <Icon
                        type="ios-lock-outline"
                        slot="prepend"
                    ></Icon>
                </Input>
            </FormItem>
            <FormItem>
                <Button
                    type="primary"
                    @click="Login()"
                    long
                >登录</Button>
            </FormItem>
        </Form>
        <Spin fix v-if="spinShow">
            <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
            <div>Loading</div>
        </Spin>
    </div>
</template>

<script>
export default {
    name: "login",
    data () {
        return {
            hr: {
                username: "",
                password: ""
            },
            spinShow:false,
        };
    },
    methods: {
        Login : function() {
            this.spinShow = true;
            this.postRequest("/login",{
                username:this.hr.username,
                password:this.hr.password,
            }).then(resp =>{
                if (resp.data.code == 200 && resp.data.error ==false){
                    this.$store.commit("login", resp.data.data.hr);
                    this.$store.commit("token", resp.data.data.token);
                    this.$store.commit("sessionId", resp.data.data.sessionId);
                    this.$Message.success("登陆成功");
                    this.spinShow = false;
                    this.$router.push({name: 'home'})
                }
                // if (resp.data.code == 401 && resp.data.error == true) {
                else{
                    this.$Message.error(resp.data.message);
                    this.spinShow = false;
                }
            }).catch(error => {
                this.spinShow = false;
                this.$Message.error("登陆失败,出现错误");
            });
        },
    }
};
</script>
<style>
    .login-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
    .login_title {
        margin: 10px auto 25px auto;
        text-align: center;
        color: #505458;
    }
</style>