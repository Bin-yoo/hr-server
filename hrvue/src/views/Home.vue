<template>
    <Layout>
        <Header>
            <Row>
                <Col span="4">
                    <h1 class="header_title">臭逼系统</h1>
                </Col>
                <Col span="1" offset="17">
                    <h3 class="header_userName">{{this.user.name}}</h3>
                </Col>
                <Col span="1">
                    <Dropdown trigger="click" style="margin-left: 20px">
                        <a href="javascript:void(0)">
                            <Avatar :src="this.user.userface" size="large" />
                        </a>
                        <DropdownMenu slot="list">
                            <DropdownItem @click.native="Logout">退出系统</DropdownItem>
                            <DropdownItem >{{this.restate.token}}</DropdownItem>
                            <DropdownItem >{{this.restate.sessionId}}</DropdownItem>
                        </DropdownMenu>
                    </Dropdown>
                </Col>
            </Row>
        </Header>
        <Layout>
            <Sider hide-trigger>Sider</Sider>
            <Content>Content</Content>
        </Layout>
        <Footer>Footer</Footer>
    </Layout>
</template>

<script>
export default {
    name: 'home',
    data(){
        return {
            menuList:[]
        }
    },
    methods: {
        Logout : function() {
            this.getRequest("/logout",null).then(resp =>{
                this.$store.commit("logout");
                this.$router.push({name: 'login'})
            }).catch(error => {
                console.log(error);
            });
        }
    },
    computed: {
      user(){
        return this.$store.state.user;
      },
      restate(){
        return this.$store.state;
      },
    }
}
</script>
<style>
    .header_title {
        color: #fff;
        font-weight: normal;
    }
    .header_userName {
        color: #fff;
    }
</style>