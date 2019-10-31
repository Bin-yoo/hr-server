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
                <Tree :data="data5" :render="renderContent"></Tree>
            </Col>
        </Row>
        <Modal
            v-model="openAdd"
            title="新增部门">
            <Row>
                <Col span="21">
                    <Form :model="department" :rules="newDepRules" :label-width="80" ref="department">
                        <FormItem label="部门名称" prop="name">
                            <Input v-model="department.name" placeholder="请输入角色名"></Input>
                        </FormItem>
                        <FormItem label="上级部门" prop="parent">
                            <treeselect v-model="department.parentId" :options="options" />
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <!-- <div slot="footer">
                <Button @click="cancel">取消</Button>
                <Button type="primary" @click="updateJobLvl">保存</Button>
            </div> -->
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
    export default {
        components: { Treeselect },
        data() {
            return {
                data5: [
                    {
                        title: '铁憨憨',
                        expand: true,
                        children: []
                    }
                ],
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
                options: [ {
                    id: '1',
                    label: 'a',
                    children: [ {
                        id: '11',
                        label: 'aa',
                    },
                    {
                        id: '12',
                        label: 'ab',
                    } ],
                    }, {
                        id: '2',
                        label: 'b',
                    }, {
                        id: '3',
                        label: 'c',
                    }
                ],
            }
        },
        mounted: function () {
        
        },
        watch: {
        
        },
        methods: {
            renderContent (h, { root, node, data }) {
                return h('span', {
                    style: {
                        display: 'inline-block',
                        width: '100%'
                    }
                }, [
                    h('span', [
                        h('Icon', {
                            props: {
                                type: 'ios-paper-outline'
                            },
                            style: {
                                marginRight: '8px'
                            }
                        }),
                        h('span', data.title)
                    ]),
                    h('span', {
                        style: {
                            display: 'inline-block',
                            float: 'right',
                            marginRight: '32px'
                        }
                    }, [
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
                const children = data.children || [];
                children.push({
                    title: 'appended node',
                    expand: true
                });
                this.$set(data, 'children', children);
            },
            remove (root, node, data) {
                const parentKey = root.find(el => el === node).parent;
                const parent = root.find(el => el.nodeKey === parentKey).node;
                const index = parent.children.indexOf(data);
                parent.children.splice(index, 1);
            }
        }
    };
</script>
<style>

    .ivu-select-dropdown{
        padding: 5px 10px;
    }

</style>