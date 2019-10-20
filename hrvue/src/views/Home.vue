<template>
    <Layout>
        <Header>
            <Row>
                <Col span="2">
                    <h1 class="header_title">臭逼系统</h1>
                </Col>
                <Col span="1" offset="19">
                    <h3 class="header_userName">{{this.user.name}}</h3>
                </Col>
                <Col span="1">
                    <Dropdown trigger="click" style="margin-left: 20px">
                        <a href="javascript:void(0)">
                            <Avatar :src="this.user.userface" size="large"/>
                        </a>
                        <DropdownMenu slot="list">
                            <DropdownItem @click.native="Logout">退出系统</DropdownItem>
                        </DropdownMenu>
                    </Dropdown>
                </Col>
            </Row>
        </Header>
        <Layout>
            <Sider hide-trigger width="250">
                <Menu width="auto">
                    <template v-for="(item,index) in this.routes">
                        <MenuItem
                            :key="item.path"
                            :name="item.path"
                            :to="item.children[0].path"
                            v-if="item.name=='员工档案'"
                        >
                            <Icon :type="item.children[0].iconCls" />
                            {{item.children[0].name}}
                        </MenuItem>
                        <Submenu :name="index+1" :key="index" v-else>
                            <template slot="title">
                                <Icon :type="item.iconCls"/>
                                {{item.name}}
                            </template>
                            <MenuItem
                                v-for="child in item.children"
                                :key="child.path"
                                :name="child.path"
                                :to="child.path">{{child.name}}
                            </MenuItem>
                        </Submenu>
                    </template>
                </Menu>
            </Sider>
            <Layout :style="{padding: '0 24px 24px'}">
                <Breadcrumb :style="{margin: '24px 0', textAlign: 'left'}">
                    <BreadcrumbItem to="/home">首页</BreadcrumbItem>
                    <BreadcrumbItem v-text="this.$router.currentRoute.name">Components</BreadcrumbItem>
                </Breadcrumb>
                <Content>
                    <keep-alive>
                        <router-view v-if="this.$route.meta.keepAlive"></router-view>
                    </keep-alive>
                    <router-view v-if="!this.$route.meta.keepAlive"></router-view>
                </Content>
            </Layout>
        </Layout>
        <Footer>Footer</Footer>
    </Layout>
</template>

<script>
    export default {
        name: 'home',
        methods: {
            Logout: function () {
                this.getRequest("/logout", null);
                this.$store.commit("logout");
                this.$router.push({name: 'login'})
            },
        },
        computed: {
            user() {
                return this.$store.state.user;
            },
            routes() {
                return this.$store.state.routes;
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