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
                >Signin</Button>
            </FormItem>
            <FormItem>
                <Button
                    type="primary"
                    @click="Check()"
                >Check</Button>
            </FormItem>
        </Form>
        <Spin size="large" fix v-if="spinShow"></Spin>
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
                console.log(resp);
                if(resp.data.code == 200 && resp.data.error ==false){
                    this.$store.commit("login", resp.data.data.hr);
                    this.$store.commit("token", resp.data.data.token);
                    this.$store.commit("sessionId", resp.data.data.sessionId);
                    this.$Message.success("登陆成功");
                    this.spinShow = false;
                    this.$router.push({name: 'home'})
                } else {
                    this.$Message.error("登陆失败");
                    this.spinShow = false;
                }
            }).catch(error => {
                this.spinShow = false;
                this.$Message.error("登陆失败");
            });
        },
        Check : function() {
            this.getRequest("/check",null).then(resp =>{
                console.log(resp);
            }).catch(error => {
                console.log(error);
            });
        }
    }
};
</script>
<style>
    .login-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
    .login_title {
        margin: 10px auto 40px auto;
        text-align: center;
        color: #505458;
    }
</style>