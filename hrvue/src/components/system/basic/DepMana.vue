<template>
    <div>
        <Row>
            <Col span="1">
                <Button type="primary" @click="openAdd=true">新增部门</Button>
            </Col>
            <!-- <Col span="6" style="marginLeft:50px">
                <Input v-model="department.depNum" search placeholder="搜索部门名称..." />
            </Col> -->
        </Row>
        <br>
        <Row>
            <Col span="8" style="textAlign:left">
                <Tree :data="depTree" :render="renderContent"></Tree>
                <Spin fix v-if="spinShow">
                    <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
                    <div>Loading</div>
                </Spin>
            </Col>
        </Row>
        <Modal
            v-model="openAdd"
            title="新增部门"
            @on-visible-change="cancel">
            <Row>
                <Col span="21">
                    <Form :model="department" :rules="newDepRules" :label-width="80" ref="departments">
                        <FormItem label="部门名称:" prop="name">
                            <Input v-model="department.name" placeholder="请输入角色名"></Input>
                        </FormItem>
                        <FormItem label="部门编号:" prop="depNum">
                            <Input v-model="department.depNum"  placeholder="请输入部门编号"></Input>
                        </FormItem>
                        <FormItem label="排序编号:" prop="orderNum">
                            <Input v-model="department.orderNum"  placeholder="请输入排序编号"></Input>
                        </FormItem>
                        <FormItem label="上级部门:" prop="parentId">
                            <treeselect v-model="department.parentId" :options="depTree" @select="normalizer" :default-expand-level="1" placeholder="请选择上级部门..."/>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="openAdd=false">取消</Button>
                <Button type="primary" @click="addDep('departments')">保存</Button>
            </div>
            <Spin fix v-if="spinShow">
                <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
                <div>Loading</div>
            </Spin>
        </Modal>
        <Modal
            v-model="openUpdate"
            title="修改部门"
            @on-visible-change="cancel">
            <Row>
                <Col span="22">
                    <Form :model="department" :rules="newDepRules" :label-width="80" ref="department">
                        <FormItem label="部门名称" prop="name">
                            <Input v-model="department.name" placeholder="请输入角色名"></Input>
                        </FormItem>
                        <FormItem label="部门编号" prop="depNum">
                            <Input v-model="department.depNum" placeholder="请输入部门编号"></Input>
                        </FormItem>
                        <FormItem label="排序编号" prop="orderNum">
                            <Input v-model="department.orderNum" placeholder="请输入排序编号"></Input>
                        </FormItem>
                        <FormItem label="上级部门" prop="parentId">
                            <treeselect v-model="department.parentId" :options="depTree" :default-expand-level="1" placeholder="请选择上级部门..."/>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="openUpdate=false">取消</Button>
                <Button type="primary" @click="updateDep('department')">保存</Button>
            </div>
            <Spin fix v-if="spinShow">
                <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
                <div>Loading</div>
            </Spin>
        </Modal>
    </div>
</template>
<script>
    //导入树形选择器组件
    import Treeselect from '@riophae/vue-treeselect'
    import '@riophae/vue-treeselect/dist/vue-treeselect.css'
    import {isNotNullORBlank} from '../../../utils/utils'
    export default {
        components: { Treeselect },
        data() {
            return {
                depTree: [],
                buttonProps: {
                    type: 'primary',
                    size: 'small',
                },
                spinShow: false,
                openAdd: false,
                openUpdate: false,
                transfer: true,
                department:{
                    id: null,
                    depNum: null,
                    orderNum: null,
                    name: '',
                    parentId: null
                },
                maxlength:10,
                newDepRules: {
                    name: [
                        {required: true, message: '部门名称不能为空', trigger: 'blur' },
                        {max:10, message: '长度不能超过10位'},
                    ],
                    depNum: [
                        {required: true, message: '部门编号不能为空', trigger: 'blur' },
                        {max:10, message: '长度不能超过10位'},
                        {pattern: /^[0-9]*$/, message: '部门编号只能为纯数字'},
                    ],
                },
            }
        },
        mounted: function () {
            this.getDeps();
        },
        watch: {
        
        },
        methods: {
            getDeps(){
                this.spinShow = true;
                this.getRequest("/system/basic/department/list").then(resp=> {
                    this.spinShow = false;
                    this.depTree = resp.data.data;
                })
            },
            renderContent (h, { root, node, data }) {
                return h('span', {
                    style: {
                        display: 'inline-block',
                        width: '100%'
                    }
                }, [
                    h('span',data.title),
                    h('span', {
                        style: {
                            display: 'inline-block',
                            float: 'right',
                            marginRight: '32px'
                        }
                    }, 
                    [
                        h('Icon', {
                            props: Object.assign({}, this.buttonProps, {
                                type: 'ios-add'
                            }),
                            style: {
                                marginRight: '8px',
                                fontSize: '16px',
                                cursor: 'pointer'
                            },
                            on: {
                                click: () => { this.append(data) }
                            }
                        }),
                        h('Icon', {
                            props: Object.assign({}, this.buttonProps, {
                                type: 'ios-create-outline'
                            }),
                            style: {
                                marginRight: '8px',
                                fontSize: '16px',
                                cursor: 'pointer'
                            },
                            on: {
                                click: () => { this.befUpdate(data) }
                            }
                        }),
                        h('Icon', {
                            props: Object.assign({}, this.buttonProps, {
                                type: 'ios-trash-outline'
                            }),
                            style: {
                                fontSize: '16px',
                                cursor: 'pointer'
                            },
                            on: {
                                click: () => { this.remove(root, node, data) }
                            }
                        })
                    ])
                ]);
            },
            append (data) {
                this.openAdd = true;
                this.department.parentId = data.id;
            },
            addDep(name){
                var check = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\s]/im;
                this.$refs[name].validate((valid) => {
                    if (check.test(this.department.name)){
                        this.$Message.error("部门名称存在特殊字符");
                        return;
                    }
                    if (valid) {
                        this.spinShow = true;
                        this.postRequest("/system/basic/department",{
                            name: this.department.name,
                            depNum: this.department.depNum,
                            orderNum: this.department.orderNum,
                            parentId: this.department.parentId
                        }).then(resp=> {
                            if (resp.data.code != 400) {
                                this.$Message.success(resp.data.data);
                                this.$refs[name].resetFields();
                                this.spinShow = false;
                                this.openAdd = false;
                                this.getDeps();
                            } else {
                                this.$Message.error(resp.data.message);
                                this.spinShow = false;
                            }
                        })
                    }
                })
            },
            befUpdate(data){
                this.openUpdate = true;
                this.department.id = data.id;
                this.department.depNum = ''+data.depNum;
                this.department.orderNum = data.orderNum;
                console.log(this.department.depNum)
                this.department.name = data.name;
                if (data.parentId === -1) {
                    this.department.parentId = null;
                } else {
                    this.department.parentId = data.parentId;
                }
            },
            updateDep(name){
                var check = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\s]/im;
                this.$refs[name].validate((valid) => {
                    if (check.test(this.department.name)){
                        this.$Message.error("部门名称存在特殊字符");
                        return;
                    }
                    if (valid) {
                        this.putRequest("/system/basic/department",{
                            id: this.department.id,
                            depNum: this.department.depNum,
                            orderNum: this.department.orderNum,
                            name: this.department.name,
                            parentId: this.department.parentId,
                        }).then(resp=> {
                            if (resp.data.code != 400) {
                                this.$Message.success(resp.data.data);
                                this.$refs[name].resetFields();
                                this.spinShow = false;
                                this.openUpdate = false;
                                this.getDeps();
                            } else {
                                this.$Message.error(resp.data.message);
                                this.spinShow = false;
                            }
                        })
                    }
                })
            },
            remove (root, node, data) {
                if(isNotNullORBlank(node.node.children)){
                    this.$Message.error("此部门还有下属部门,无法删除");
                } else {
                    this.$Modal.confirm({
                        title: '你正在进行删除操作',
                        content: '<p>你确定要删除该部门吗?</p>',
                        onOk: () => {
                            var _this = this;
                            this.deleteRequest("/system/basic/department/" + node.node.id).then(resp=> {
                                if(resp.data.code != 400){
                                    this.$Message.success(resp.data.data);
                                    this.spinShow = false;
                                    _this.getDeps();
                                }else{
                                    this.$Message.error(resp.data.message);
                                }
                            })
                        },
                    });
                }
            },
            cancel(flag){
                if(flag == false){
                    this.$refs['departments'].resetFields();
                    this.$refs['department'].resetFields();
                }
            },
            normalizer(node,instanceId) {
                console.log("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                console.log(node);
                console.log("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                console.log(instanceId);
            },
        }
    };
</script>
<style>

    .ivu-select-dropdown{
        padding: 5px 10px;
    }

</style>