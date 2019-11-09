<template>
    <div>
        <Row>
            <Col span="22">
                <Row :gutter="6">
                    <Col span="3">
                        <treeselect class="departDown" v-model="souformItem.departmentId" :options="dropDownList.departmentList" :default-expand-level="1" placeholder="请选择部门"/>                            
                    </Col>
                    <Col span="2">
                        <Select v-model="souformItem.positionId" placeholder="职位" clearable>
                            <Option v-for="item in dropDownList.positionList" :value="item.id" :key="item.id">{{item.name}}</Option>
                        </Select>
                    </Col>
                    <Col span="2">
                        <Select v-model="souformItem.jobLevelId" placeholder="职称" clearable>
                            <Option v-for="item in dropDownList.jobLevelList" :value="item.id" :key="item.id">{{item.name}}</Option>
                        </Select>
                    </Col>
                    <Col span="5">
                        <Input v-model="souformItem.name" clearable placeholder="请输入姓名..." />
                    </Col>
                    <Col span="1">
                        <Button icon="ios-search" @click="getRpList">搜索</Button>
                    </Col>
                </Row>
            </Col>
            <Col span="2"><Button type="primary" @click="addModal = true">添加奖惩记录</Button></Col>
        </Row>
        <br>
        <Row>
            <Table border ref="selection" :columns="columns" :data="rpLists">
                <template slot-scope="{ row, index }" slot="action">
                    <Button type="primary" style="margin-right: 5px" @click="update(index)">编辑</Button>
                    <Button type="error" @click="remove(index)">删除</Button>
                </template>
          </Table>
        </Row>
        <Row :style="{margin: '20px 0 0 0'}">
            <Col span="1"><Button type="error">批量删除</Button></Col>
            <Page :total="total" show-sizer show-elevator show-total @on-change="pageChange" @on-page-size-change="onPageSizeChange"/>
        </Row>
        <Modal
            v-model="addModal"
            title="添加奖惩记录"
            width=30%
            @on-ok="ok"
            @on-cancel="cancel">
            
            <Form :model="formItem" :label-width="80">
                <Row>
                    <Col span="12">
                        <FormItem label="部门："><!-- 级联菜单1 -->
                            <Select v-model="formItem.department">
                                <Option value="0">人事部</Option>
                                <Option value="1">财务部</Option>
                                <Option value="2">技术部</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="职位："><!-- 级联菜单2 -->
                            <Select v-model="formItem.position">
                                <Option value="0">教授</Option>
                                <Option value="1">教师</Option>
                                <Option value="2">教务管理人员</Option>
                                <Option value="3">其他</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="姓名："><!-- 级联菜单3 -->
                            <Select v-model="formItem.name">
                                <Option value="0">张三</Option>
                                <Option value="1">李四</Option>
                                <Option value="2">王五</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="奖惩日期：">
                            <DatePicker type="date" placeholder="选择奖惩日期" v-model="formItem.date"></DatePicker>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="奖惩类型："><!-- 级联菜单1 -->
                            <RadioGroup v-model="formItem.type">
                                    <Radio label="0">奖励</Radio>
                                    <Radio label="1">惩罚</Radio>
                                </RadioGroup>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="奖惩原因：">
                            <Input v-model="formItem.season" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="奖惩分数：">
                            <Input v-model="formItem.grade" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                        <FormItem label="备注：">
                            <Input v-model="formItem.remark" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
        </Modal>
        <Modal
            v-model="updateModal"
            title="修改奖惩记录"
            width=30%
            @on-ok="ok"
            @on-cancel="cancel">
            
            <Form :model="formItem" :label-width="80">
                <Row>
                    <Col span="12">
                        <FormItem label="部门："><!-- 级联菜单1 -->
                            <Select v-model="formItem.department">
                                <Option value="0">人事部</Option>
                                <Option value="1">财务部</Option>
                                <Option value="2">技术部</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="职位："><!-- 级联菜单2 -->
                            <Select v-model="formItem.position">
                                <Option value="0">教授</Option>
                                <Option value="1">教师</Option>
                                <Option value="2">教务管理人员</Option>
                                <Option value="3">其他</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="姓名："><!-- 级联菜单3 -->
                            <Select v-model="formItem.name">
                                <Option value="0">张三</Option>
                                <Option value="1">李四</Option>
                                <Option value="2">王五</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="奖惩日期：">
                            <DatePicker type="date" placeholder="选择奖惩日期" v-model="formItem.date"></DatePicker>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="奖惩类型："><!-- 级联菜单1 -->
                            <RadioGroup v-model="formItem.type">
                                    <Radio label="0">奖励</Radio>
                                    <Radio label="1">惩罚</Radio>
                                </RadioGroup>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="奖惩原因：">
                            <Input v-model="formItem.season" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="奖惩分数：">
                            <Input v-model="formItem.grade" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                        <FormItem label="备注：">
                            <Input v-model="formItem.remark" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
        </Modal>
    </div>
</template>

<script>
    import Treeselect from '@riophae/vue-treeselect'
    import '@riophae/vue-treeselect/dist/vue-treeselect.css'
    export default {
        components: { Treeselect },
        name: 'PerMv',
            data() {
                return {
                    addModal: false,
                    updateModal: false,
                    page: 1,
                    total: 100,
                    limit: 10,
                    dropDownList: [],
                    rpList: {},
                    rpLists: [],
                    souformItem:{
                        departmentId: null,
                        positionId: null,
                        jobLevelId: null,
                        name: '',
                    },
                    formItem:{
                        name: '',       //名字
                        gender:'',      //性别
                        department:'',  //部门
                        date:'',    //奖惩日期
                        type:'',    //奖惩类型
                        season:'',    //奖惩原因
                        grade:'',    //奖惩分数
                        remark:'',    //备注
                    },
                    columns: [
                            {
                                type: 'selection',
                                width: 60,
                                align: 'center'
                            },
                            {
                                title: '姓名',
                                key: 'name',
                                render: (h, params) => {
                                    return h('span', params.row.employee.name);
                                }
                            },
                            {
                                title: '工号',
                                key: 'workId',
                                render: (h, params) => {
                                    return h('span', params.row.employee.workId);
                                }
                            },
                            {
                                title: '部门',
                                key: 'departmentName',
                                render: (h, params) => {
                                    return h('span', params.row.employee.departmentName);
                                }
                            },
                            {
                                title: '职位',
                                key: 'positionName',
                                render: (h, params) => {
                                    return h('span', params.row.employee.positionName);
                                }
                            },
                            {
                                title: '日期',
                                key: 'date'
                            },
                            {
                                title: '奖惩类别',
                                key: 'type'
                            },
                            {
                                title: '奖惩原因',
                                key: 'reason'
                            },
                            {
                                title: '奖惩分数',
                                key: 'point'
                            },
                            {
                                title: '备注',
                                key: 'remark'
                            },
                            {
                                title: '操作',
                                slot: 'action',
                                width: 175,
                                align: 'center'
                            }
                        ],
                    data1: [
                            {
                                name: '宇哥',
                                jobNum: 20191016001,
                                department: "人事部",
                                position: "人事部经理",
                                date: "2019年10月16日",
                                category: "惩罚",
                                reason: "迟到",
                                fraction: "-2",
                                remarks: "无",
                            },
                    ]
                }
            },
        methods: {
            onPageSizeChange(index){
                this.limit = index;
            },
            pageChange(index){
                this.page = index;
                console.log(page)
            },
            getDropDownList(){
                this.getRequest("/employee/init").then(resp=> {
                    this.dropDownList = resp.data.data;
                })
            },
            getRpList(){
                this.getRequest("/rp/allRp", {
                    page: this.page,
                    limit: this.limit,
                    departmentId: this.souformItem.departmentId,
                    positionId: this.souformItem.positionId,
                    //jobLevelId: this.souformItem.jobLevelId,
                    name: this.souformItem.name,
                }).then(resp => {
                    this.rpLists = resp.data.data.list;
                    this.total = resp.data.data.total;
                })
            },
            ok () {
                this.$Message.info('Clicked ok');
            },
            cancel () {
                this.$Message.info('Clicked cancel');
            }, 
            update (index) {
                this.updateModal = true;
                console.log(index);
            },
        },
        mounted: function (){
            this.getDropDownList();
            this.getRpList();
        },
        watch: {
            page: "getRpList",
            limit: "getRpList",
        },
    }
</script>

