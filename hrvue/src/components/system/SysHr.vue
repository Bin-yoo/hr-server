<template>
    <Tabs value="RoleMana">
        <TabPane label="权限管理" name="RoleMana">
            <Row>
                <Col span="4">
                    <Input v-model="newRole" placeholder="请输入角色的英文名">
                        <span slot="prepend">http://</span>
                        <span slot="append">.com</span>
                    </Input>
                </Col>
                <Col span="4" offset="1">
                    <Input v-model="newRoleZh" placeholder="请输入角色的中文名"></Input>
                </Col>
                <Col span="2">
                    <Button type="primary">添加角色</Button>
                </Col>
            </Row>
            <Row :style="{marginTop: '20px', textAlign: 'left'}">
                <Col span="10">
                    <Collapse v-model="activeColItem" accordion @on-change="collapseChange">
                        <Panel v-for="item in roles" :key="item.id">
                            {{item.nameZh}}
                            <p slot="content">
                                <Row>
                                    <Tree :data="treeData" show-checkbox></Tree>
                                </Row>
                                <Row :style="{marginTop: '20px'}">
                                    <Col span="3">
                                        <Button type="error">删除角色</Button>
                                    </Col>
                                    <Col span="3" offset="15">
                                        <Button>取消修改</Button>
                                    </Col>
                                    <Col span="3">
                                        <Button type="primary">保存修改</Button>
                                    </Col>
                                </Row>
                            </p>
                        </Panel>
                    </Collapse>
                </Col>
                <Spin fix v-if="spinShow">
                    <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
                    <div>Loading</div>
                </Spin>
            </Row>
        </TabPane>
        <TabPane label="角色管理" name="CharacterMana">标签一的内容</TabPane>
    </Tabs>
</template>
<script>
    export default{
        data(){
            return {
                activeColItem: "-1",
                newRole: '',
                newRoleZh: '',
                roles: [],
                treeData: [],
                spinShow: false,
            }
        },
        mounted: function () {
            this.initRoles();
        },
        methods: {
            initRoles(){
                this.spinShow = true;
                this.getRequest("/system/basic/roles").then(resp=> {
                    console.log(resp);
                    this.spinShow = false;
                    if (resp.status == 200 && resp.data.data != null) {
                        this.roles = resp.data.data;
                        this.activeColItem = "-1";
                    }
                })
            },
            collapseChange(activeName){
                if (activeName == '') {
                    return;
                }
                var Roleid = this.roles[activeName].id;
                this.getRequest("/system/basic/menuTree/" + Roleid).then(resp=> {
                    if (resp && resp.status == 200) {
                        var data = resp.data;
                        this.treeData = data.menus;
                        this.checkedKeys = data.mids;
                    }
                })
            },
        }
    }
</script>
<style>

</style>
