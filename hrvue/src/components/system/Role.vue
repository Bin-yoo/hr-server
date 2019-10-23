<template>
    <div>
        <Row>
            <Col span="4">
                <Input v-model="keyword" placeholder="输入角色名进行查询" />
            </Col>
            <Col span="2">
                <Button type="primary" icon="ios-search">查询</Button>
            </Col>
            <Col span="2" offset="16">
                <Button type="primary" icon="ios-add" @click="openAddNew=true">添加新角色</Button>
            </Col>
        </Row>
        <br>
        <Row>
            <Col span="24">
                <Table  border  :columns="columns1" :data="roles" :loading="loading">
                    <template slot-scope="{ row, index }" slot="action">
                        <Row>
                            <Col span="8">
                                <Button type="primary" size="small" @click="beforeUpdate(index)">编辑信息</Button>
                            </Col>
                            <Col span="10">
                            <Button type="primary" size="small">编辑权限</Button>
                            </Col>
                            <Col span="4">
                                <Button type="error" size="small" @click="deleteRole(row.id)">删除</Button>
                            </Col>
                        </Row>
                    </template>
                </Table>
            </Col>
        </Row>
        <br>
        <Row>
            <Col>
                <Page :total="total" show-sizer show-elevator show-total @on-change="pageChange" @on-page-size-change="onPageSizeChange"/>
            </Col>
        </Row>
        <Modal
            v-model="openAddNew"
            title="添加新角色">
            <Row>
                <Col span="21">
                    <Form :model="newRole" :rules="newRoleRules" :label-width="80" ref="newRole">
                        <FormItem label="角色名" prop="name">
                            <Input v-model="newRole.name" placeholder="请输入角色名"></Input>
                        </FormItem>
                        <FormItem label="备注" prop="remark">
                            <Input v-model="newRole.remark" type="textarea" placeholder="备注"></Input>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="cancel">取消</Button>
                <Button type="primary" @click="addNewRole">保存</Button>
            </div>
            <Spin fix v-if="spinShow">
                <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
                <div>Loading</div>
            </Spin>
        </Modal>
        <Modal
            v-model="openUpdate"
            title="编辑角色信息">
            <Row>
                <Col span="21">
                    <Form :model="role" :rules="newRoleRules" :label-width="80" ref="role">
                        <FormItem label="角色名" prop="name">
                            <Input v-model="role.name" placeholder="请输入角色名"></Input>
                        </FormItem>
                        <FormItem label="备注" prop="remark">
                            <Input v-model="role.remark" type="textarea" placeholder="备注"></Input>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="cancel">取消</Button>
                <Button type="primary" @click="updateRole">保存</Button>
            </div>
            <Spin fix v-if="spinShow">
                <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
                <div>Loading</div>
            </Spin>
        </Modal>
    </div>
    <!-- <Tabs value="RoleMana">
        <TabPane label="角色/权限管理" name="RoleMana">
            <menu-role></menu-role>
        </TabPane>
        <TabPane label="用户角色管理" name="CharacterMana">
            <user-role></user-role>
        </TabPane>
    </Tabs> -->
</template>
<script>
    import MenuRole from './role/MenuRole.vue'
    import UserRole from './role/UserRole.vue'
    import {isNotNullORBlank} from '../../utils/utils'
    export default{
        components: {
            'menu-role': MenuRole,
            'user-role': UserRole,
        },
        data(){
            return {
                date: new Date(),
                columns1: [
                    {
                        title: '角色名称',
                        key: 'name'
                    },
                    {
                        title: '创建时间',
                        key: 'createDate'
                    },
                    {
                        title: '备注',
                        key: 'remark'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 250,
                        align: 'center'
                    }
                ],
                roles: [
                    {id: 1, name: 'didi', remark: 'sss'}
                ],
                keyword: '',
                page:1,
                total: 100,
                limit: 10,
                loading: false,
                spinShow: false,
                openAddNew : false,
                openUpdate: false,
                newRole: {
                    name: '',
                    remark: ''
                },
                newRoleRules: {
                    name: [
                        {required: true, message: '用户名不能为空', trigger: 'blur' }
                    ],
                    remark: [
                        { type: 'string', max: 50, message: '备注长度不能超过50个字符', trigger: 'change' }
                    ]
                },
                role: {
                    id: 0,
                    name: '',
                    remark: ''
                },
            }
        },
        mounted: function () {
            // this.getRoleList();
        },
        watch: {
            page: "getRoleList",
            limit: "getRoleList",
        },
        methods: {
            onPageSizeChange(index){
                this.limit = index;
            },
            pageChange(index){
                this.page = index;
            },
            getRoleList(){
                this.loading = true;
                this.getRequest("/system/role/roles",{
                    page: this.page,
                    limit: this.limit,
                }).then(resp=> {
                    this.loading = false;
                    this.roles = resp.data.data;
                })
            },
            addNewRole(){
                var check = /\s/;
                if(!check.test(this.newRole.name) && isNotNullORBlank(this.newRole.name)){
                    this.spinShow = true;
                    this.postRequest("/system/role/addRole", {
                        name: this.newRole.name,
                        remark: this.newRole.remark
                    }).then(resp=> {
                        if (resp.data.error == false && resp.data.code == 200) {
                            this.getRoleList();
                            this.newRole.name = '';
                            this.newRole.remark = '';
                            this.$Message.success(resp.data.data);
                            this.spinShow = false;
                            this.openAddNew = false;
                        } else {
                            this.$Message.error(resp.data.message);
                            this.spinShow = false;
                        }
                    })
                } else {
                    this.$Message.error("角色名称不能为空");
                }
            },
            beforeUpdate(index){
                this.openUpdate = true;
                this.role = this.roles[index];
            },
            updateRole(){
                var check = /\s/;
                if(!check.test(this.newRole.name) && isNotNullORBlank(this.newRole.name)){
                    this.spinShow = true;
                    this.postRequest("/system/role/updateRole", {
                        id: this.role.id,
                        name: this.role.name,
                        remark: this.role.remark
                    }).then(resp=> {
                        if (resp.data.error == false && resp.data.code == 200) {
                            this.getRoleList();
                            this.role.id = 0;
                            this.role.name = '';
                            this.role.remark = '';
                            this.$Message.success(resp.data.data);
                            this.spinShow = false;
                            this.openUpdate = false;
                        } else {
                            this.$Message.error(resp.data.message);
                            this.spinShow = false;
                        }
                    })
                } else {
                    this.$Message.error("角色名称不能为空");
                }
            },
            cancel(){
                this.openAddNew = false;
                this.openUpdate = false;
                this.newRole.name = '';
                this.newRole.remark = '';
            },
            deleteRole(id){
                console.log(id);
                this.$Modal.confirm({
                    title: '你正在进行删除操作',
                    content: '<p>删除后相关账号的权限将更改为"普通员工"</p><p>你确定要删除该角色吗?</p>',
                    onOk: () => {
                        this.deleteRequest("/system/role/role/" + id).then(resp=> {
                            this.$Message.success(resp.data.data);
                            this.spinShow = false;
                            this.initRoles();
                        }).catch(error => {
                            this.spinShow = false;
                            this.$Message.error(error);
                        });
                    },
                });
            }
        }
    }
</script>
<style>

</style>
