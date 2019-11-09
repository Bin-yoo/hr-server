<template>
    <div>
        <Row>
            <Col span="4">
                <Input v-model="keyword" placeholder="输入角色名进行查询" @on-change="check" clearable/>
            </Col>
            <Col span="2">
                <Button type="primary" icon="ios-search" @click="getRoleList">查询</Button>
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
                            <Button type="primary" style="margin-right: 5px" @click="beforeUpdate(index)" :disabled="row.id === 2 ? true : false">编辑信息</Button>
                            <Button type="primary" style="margin-right: 5px" @click="getMenuTree(row.id)">编辑权限</Button>
                            <Button type="error" style="margin-right: 5px" @click="deleteRole(row.id)" :disabled="row.id === 2 ? true : false">删除</Button>
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
                        <FormItem label="角色名:" prop="name">
                            <Input v-model="newRole.name" placeholder="请输入角色名"></Input>
                        </FormItem>
                        <FormItem label="备注:" prop="remark">
                            <Input v-model="newRole.remark" type="textarea" placeholder="备注"></Input>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="cancel('newRole')">取消</Button>
                <Button type="primary" @click="addNewRole('newRole')">保存</Button>
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
                        <FormItem label="角色名:" prop="name">
                            <Input v-model="role.name" placeholder="请输入角色名"></Input>
                        </FormItem>
                        <FormItem label="备注:" prop="remark">
                            <Input v-model="role.remark" type="textarea" placeholder="备注"></Input>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="cancel('role')">取消</Button>
                <Button type="primary" @click="updateRole('role')">保存</Button>
            </div>
            <Spin fix v-if="spinShow">
                <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
                <div>Loading</div>
            </Spin>
        </Modal>
        <Modal
            v-model="openRole"
            title="编辑角色权限"
            footer-hide
            width="400">
            <Tabs value="RoleMana">
                <TabPane label="模块(菜单)访问权限" name="RoleMana">
                    <Row>
                        <Col>
                            <Tree :data="treeData" ref="tree" show-checkbox multiple></Tree>
                        </Col>
                    </Row>
                    <br>
                    <Row type="flex" justify="center">
                        <Col span="24">
                            <Button type="primary" @click="updateTree" long>保存</Button>
                        </Col>
                    </Row>
                </TabPane>
                <TabPane label="操作权限" name="CharacterMana">
                    <!-- <user-role></user-role> -->滴滴滴
                </TabPane>
            </Tabs>
            <Spin fix v-if="spinShow">
                <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
                <div>Loading</div>
            </Spin>
        </Modal>
    </div>
</template> 
<script>
    import {isNotNullORBlank} from '../../utils/utils'
    export default{
        data(){
            return {
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
                        width: 280,
                        align: 'center'
                    }
                ],
                roles: [
                    // {id: 1, name: 'didi', remark: 'sss'}
                ],
                keyword: '',
                page:1,
                total: 100,
                limit: 10,
                loading: false,
                spinShow: false,
                openAddNew : false,
                openUpdate: false,
                openRole: false,
                newRole: {
                    name: '',
                    remark: ''
                },
                newRoleRules: {
                    name: [
                        {required: true, message: '角色名不能为空', trigger: 'blur' },
                        {max:10, message: '长度不能超过10位'},
                    ],
                    remark: [
                        {type: 'string', max: 50, message: '备注长度不能超过50个字符'}
                    ]
                },
                role: {
                    id: 0,
                    name: '',
                    remark: ''
                },
                treeData: [],
                treeRid:'',
            }
        },
        mounted: function () {
            this.getRoleList();
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
            check(){
                if(!isNotNullORBlank(this.keyword)){
                    this.getRoleList();
                }
            },
            getRoleList(){
                this.loading = true;
                this.getRequest("/system/role/roles",{
                    page: this.page,
                    limit: this.limit,
                    name: this.keyword,
                }).then(resp=> {
                    this.loading = false;
                    this.roles = resp.data.data.list;
                    this.total = resp.data.data.total;
                })
            },
            addNewRole(name){
                var check = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\s]/im;
                this.$refs[name].validate((valid) => {
                    if (check.test(this.newRole.name)){
                        this.$Message.error("角色名称存在特殊字符");
                        return;
                    }
                    if (valid) {
                        this.spinShow = true;
                        this.postRequest("/system/role/addRole", {
                            name: this.newRole.name,
                            remark: this.newRole.remark
                        }).then(resp=> {
                            if (resp.data.error == false && resp.data.code == 200) {
                                this.getRoleList();
                                this.$refs[name].resetFields();
                                this.$Message.success(resp.data.data);
                                this.spinShow = false;
                                this.openAddNew = false;
                            } else {
                                this.$Message.error(resp.data.message);
                                this.spinShow = false;
                            }
                        })
                    }
                })
            },
            beforeUpdate(index){
                this.openUpdate = true;
                this.role.id = this.roles[index].id;
                this.role.name = this.roles[index].name;
                this.role.remark = this.roles[index].remark;
            },
            updateRole(name){
                var check = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\s]/im;
                this.$refs[name].validate((valid) => {
                    if (check.test(this.role.name)){
                        this.$Message.error("角色名称存在特殊字符");
                        return;
                    }
                    if (valid) {
                        this.spinShow = true;
                        this.putRequest("/system/role/updateRole", {
                            id: this.role.id,
                            name: this.role.name,
                            remark: this.role.remark
                        }).then(resp=> {
                            if (resp.data.error == false && resp.data.code == 200) {
                                this.getRoleList();
                                this.$refs[name].resetFields();
                                this.$Message.success(resp.data.data);
                                this.spinShow = false;
                                this.openUpdate = false;
                            } else {
                                this.$Message.error(resp.data.message);
                                this.spinShow = false;
                            }
                        })
                    }
                })
            },
            cancel(name){
                this.openAddNew = false;
                this.openUpdate = false;
                this.openRole = false;
                this.$refs[name].resetFields();
            },
            deleteRole(id){
                this.$Modal.confirm({
                    title: '你正在进行删除操作',
                    content: '<p>删除后相关账号的权限将更改为"普通员工"</p><p>你确定要删除该角色吗?</p>',
                    onOk: () => {
                        var _this = this;
                        this.deleteRequest("/system/role/role/" + id).then(resp=> {
                            this.$Message.success(resp.data.data);
                            this.spinShow = false;
                            _this.getRoleList();
                        })
                    },
                });
            },
            getMenuTree(id){
                this.openRole = true;
                this.getRequest("/system/role/menuTree/" + id).then(resp=> {
                    if (resp && resp.status == 200) {
                        this.treeData = resp.data.data;
                    }
                })
                this.treeRid = id;
            },
            updateTree(){
                var treeCheckedData = this.$refs.tree.getCheckedNodes();
                var checkedKeys = [];
                treeCheckedData.forEach(item => {
                    if(item.children == null){
                        checkedKeys.push(item.id)
                    }
                });
                this.putRequest("/system/role/updateMenuTree", {
                    rid: this.treeRid,
                    checkedKeys: checkedKeys
                }).then(resp=> {
                    this.$Message.success(resp.data.data);
                    this.spinShow = false;
                }).catch(error => {
                    this.spinShow = false;
                    this.$Message.error(error);
                });
            },
        }
    }
</script>
<style>

</style>
