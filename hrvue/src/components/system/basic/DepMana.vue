<template>
    <div>
        <Row>
            <Col span="1">
                <Button type="primary" @click="openAdd=true">新增部门</Button>
            </Col>
            <Col span="6" style="marginLeft:50px">
                <Input v-model="department.parentId" search placeholder="搜索部门名称..." />
            </Col>
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
            @on-visible-change='cancel'>
            <Row>
                <Col span="21">
                    <Form :model="department" :rules="newDepRules" :label-width="80" ref="department">
                        <FormItem label="部门名称" prop="name">
                            <Input v-model="department.name" placeholder="请输入角色名"></Input>
                        </FormItem>
                        <FormItem label="上级部门" prop="parent">
                            <treeselect v-model="department.parentId" :options="depTree" :default-expand-level="1"/>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="openAdd=false">取消</Button>
                <Button type="primary" @click="addDep">保存</Button>
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
                department:{
                    name: '',
                    parentId: null
                },
                newDepRules: {
                    name: [
                        {required: true, message: '职称名称不能为空', trigger: 'blur' }
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
                        h('Button', {
                            props: Object.assign({}, this.buttonProps, {
                                icon: 'ios-add'
                            }),
                            style: {
                                marginRight: '8px'
                            },
                            on: {
                                click: () => { this.append(data) }
                            }
                        }),
                        h('Button', {
                            props: Object.assign({}, this.buttonProps, {
                                icon: 'ios-remove'
                            }),
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
            addDep(){
                this.spinShow = true;
                this.postRequest("/system/basic/department",{
                    name: this.department.name,
                    parentId: this.department.parentId
                }).then(resp=> {
                    this.$Message.success(resp.data.data);
                    this.spinShow = false;
                    this.openAdd = false;
                    this.getDeps();
                })
            },
            remove (root, node, data) {
                console.log(node.node.children)
                if(isNotNullORBlank(node.node.children)){
                    this.$Message.error("此部门还有下属部门,无法删除");
                } else {
                    this.$Modal.confirm({
                        title: '你正在进行删除操作',
                        content: '<p>你确定要删除该部门吗?</p>',
                        onOk: () => {
                            var _this = this;
                            this.deleteRequest("/system/basic/department/" + node.node.id).then(resp=> {
                                this.$Message.success(resp.data.data);
                                this.spinShow = false;
                                _this.getDeps();
                            })
                        },
                    });
                }
            },
            cancel(flag){
                if(flag == false){
                    this.department.name = '';
                    this.department.parentId = null;
                }
            }
        }
    };
</script>
<style>

    .ivu-select-dropdown{
        padding: 5px 10px;
    }

</style>