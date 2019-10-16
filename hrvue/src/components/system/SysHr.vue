<template>
    <Tabs value="RoleMana">
        <TabPane label="权限管理" name="RoleMana">
            <Row type="flex" justify="start" >
                <Col span="10">
                    <Form :rules="rules" :model="newRole" ref="newRole" inline>
                        <FormItem prop="role">
                            <Input v-model="newRole.role" placeholder="请输入角色的英文名">
                                <span slot="prepend">ROLE_</span>
                            </Input>
                        </FormItem>
                        <FormItem prop="roleZh">
                            <Input v-model="newRole.roleZh" placeholder="请输入角色的中文名"></Input>
                        </FormItem>
                        <FormItem>
                            <Button type="primary" @click="addNewRole" :loading="spinShow">添加角色</Button>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <Row :style="{textAlign: 'left'}">
                <Col span="10">
                    <Collapse accordion @on-change="collapseChange">
                        <Panel v-for="item in roles" :key="item.id">
                            {{item.nameZh}}
                            <p slot="content">
                                <Row>
                                    <Tree :data="treeData" show-checkbox multiple></Tree>
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
                    <Spin fix v-if="spinShow">
                        <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
                        <div>Loading</div>
                    </Spin>
                </Col>
            </Row>
        </TabPane>
        <TabPane label="角色管理" name="CharacterMana">标签一的内容</TabPane>
    </Tabs>
</template>
<script>
    import {isNotNullORBlank} from '../../utils/utils'
    export default{
        data(){
            return {
                rules: {
                    role: [
                        {required: true, message: '角色英文名不能为空', trigger: 'blur' }
                    ],
                    roleZh: [
                        {required: true, message: '角色中文名不能为空', trigger: 'blur' }
                    ]
                },
                newRole: {
                    role: '',
                    roleZh: ''
                },
                roles: [],
                treeData: [],
                checkedKeys: [],
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
                    this.spinShow = false;
                    if (resp.status == 200 && resp.data.data != null) {
                        this.roles = resp.data.data;
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
                        this.treeData = resp.data.data;
                    }
                })
            },
            addNewRole(){
                var check = /\s/;
                if(!check.test(this.newRole.role) && !check.test(this.newRole.roleZh) && isNotNullORBlank(this.newRole.role,this.newRole.roleZh)){
                    this.spinShow = true;
                    this.postRequest("/system/basic/role", {
                        name: this.newRole.role,
                        nameZh: this.newRole.roleZh
                    }).then(resp=> {
                        if (resp.data.error == false && resp.data.code == 200) {
                            this.initRoles();
                            this.newRole.role = '';
                            this.newRole.roleZh = '';
                            this.loading = false;
                        }
                    }).catch(error => {
                        this.spinShow = false;
                        this.$Message.error(error);
                    });
                } else {
                    this.$Message.error("角色中英文名称不能为空");
                }
            },
        }
    }
</script>
<style>

</style>
