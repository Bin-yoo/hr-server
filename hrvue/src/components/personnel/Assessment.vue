<template>
    <div>
        <Row>
            <Col span="22">
                <Form :model="souformItem">
                    <Row :gutter="6">
                        <Col span="2">
                            <FormItem>
                                <Select v-model="formItem.departmentID" placeholder="考核状态">
                                    <Option value="0">已开始</Option>
                                    <Option value="1">未开始</Option>
                                    <Option value="2">已结束</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="5">
                            <FormItem>
                                <Input v-model="formItem.input" clearable placeholder="请输入..."/>
                            </FormItem>
                        </Col>
                        <Col span="1">
                            <FormItem>
                                <Button icon="ios-search">搜索</Button>
                            </FormItem>
                        </Col>
                    </Row>
                </Form>
            </Col>
            <Col span="2">
                <Button type="primary" @click="addModal = true">添加考核</Button>
            </Col>
        </Row>
        <Row>
            <Table border ref="selection" :columns="columns" :data="data1">
                <template slot-scope="{ row, index }" slot="action">
                    <Button type="primary" style="margin-right: 5px" @click="update(index)">开始考核</Button>
                    <Button type="error" @click="remove(index)">删除</Button>
                </template>
            </Table>
        </Row>
        <Row :style="{margin: '20px 0 0 0'}">
            <Page :total="100" show-elevator/>
        </Row>
        <Modal
                v-model="updateModal"
                title="调薪"
                width=30%
                @on-ok="ok"
                @on-cancel="cancel">
            <p>Content of dialog</p>
            <p>Content of dialog</p>
            <p>Content of dialog</p>
        </Modal>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                addModal: false,
                updateModal: false,
                souFormItem: {
                    name: '',       //名字
                    gender: '',      //性别
                    department: '',  //部门
                    position: '',    //职位
                    jobLevelID: '',    //职称
                    input: '',          //模糊查询
                },
                formItem: {
                    name: '',       //名字
                    gender: '',      //性别
                    department: '',  //部门
                    date: '',    //考核日期
                    result: '',    //考核结果
                    content: '',    //考核内容
                    remark: '',    //备注
                },
                columns: [
                    {
                        title: '考核编号',
                        key: 'id'
                    },
                    {
                        title: '考核名称',
                        key: 'name'
                    },
                    {
                        title: '备注',
                        key: 'remarks'
                    },
                    {
                        title: '状态',
                        key: 'state'
                    },
                    {
                        title: '开始时间',
                        key: 'beginDate'
                    },
                    {
                        title: '结束时间',
                        key: 'endDate'
                    },
                    {
                        title: '创建时间',
                        key: 'createDate'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 280,
                        align: 'center'
                    }
                ],
                data1: [
                    {
                        id: 1,
                        name:"2019年第一季度考核",
                        remarks: "2019年第一季度考核",
                        state: "未开始",
                        beginDate: "2019年1月16日",
                        endDate: "2019年3月16日",
                        createDate: "2019年1月15日",
                    },
                    {
                        id: 1,
                        name:"2019年第一季度考核",
                        remarks: "2019年第一季度考核",
                        state: "未开始",
                        beginDate: "2019年1月16日",
                        endDate: "2019年3月16日",
                        createDate: "2019年1月15日",
                    },
                    {
                        id: 1,
                        name:"2019年第一季度考核",
                        remarks: "2019年第一季度考核",
                        state: "未开始",
                        beginDate: "2019年1月16日",
                        endDate: "2019年3月16日",
                        createDate: "2019年1月15日",
                    },
                ]
            }
        },
        methods: {
            ok() {
                this.$Message.info('Clicked ok');
            },
            cancel() {
                this.$Message.info('Clicked cancel');
            },
            update(index) {
                this.updateModal = true;
                console.log(index);
            },
        }
    }
</script>