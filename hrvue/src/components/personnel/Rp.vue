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
                    <Col span="5">
                        <Input v-model="souformItem.name" clearable placeholder="请输入姓名..." />
                    </Col>
                    <Col span="1">
                        <Button icon="ios-search" @click="getRpList">搜索</Button>
                    </Col>
                </Row>
            </Col>
            <Col span="2">
                <Button type="primary" @click="befaddRp">添加奖惩记录</Button>
            </Col>
        </Row>
        <br>
        <Row>
            <Table border ref="selection" :columns="columns" :data="rpLists">
                <template slot-scope="{ row, index }" slot="action">
                    <Button type="primary" style="margin-right: 5px" @click="beforeUpdate(index)">编辑</Button>
                    <Button type="error" @click="remove(row.id)">删除</Button>
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
            @on-visible-change="cancel">
            
            <Form ref="rpList" :model="rpList" :rules="rpRules" :label-width="90">
                <Row>
                    <Col span="12">
                        <FormItem label="选择员工：" prop="eid">
                            <Button type="primary" style="margin-right: 5px" @click="selectEmp">选择</Button>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="姓名：">
                            {{rpList.name}}
                        </FormItem>
                    </Col>
                    <Col span="6">
                        <FormItem label="工号：">
                            {{rpList.workId}}
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="所属部门：">
                            {{rpList.departmentName}}
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="职位：">
                            {{rpList.positionName}}
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="奖惩类型：" prop="type">
                            <RadioGroup v-model="rpList.type">
                                <Radio label="奖励">奖励</Radio>
                                <Radio label="惩罚">惩罚</Radio>
                            </RadioGroup>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="奖惩日期：" prop="date">
                            <DatePicker type="date" placeholder="选择奖惩日期" v-model="rpList.date"></DatePicker>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="奖惩分数：" prop="point">
                            <Input v-model="rpList.point" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="奖惩原因：" prop="reason">
                            <Input v-model="rpList.reason" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                        <FormItem label="备注：" prop="remark">
                            <Input v-model="rpList.remark" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
            <div slot="footer">
                <Button @click="addhandleReset('rpList')">重置</Button>
                <Button type="primary" @click="addRp('rpList')">保存</Button>
            </div>
        </Modal>
        <Modal
            v-model="selectEmpModal"
            title="选择员工"
            width=40%
            @on-visible-change="cancel">
            
            <Row :gutter="6">
                <Col span="6">
                    <treeselect class="departDown" v-model="souformItem2.departmentId" :options="dropDownList.departmentList" :default-expand-level="1" placeholder="请选择部门"/>                            
                </Col>
                <Col span="6">
                    <Select v-model="souformItem2.positionId" placeholder="职位" clearable>
                        <Option v-for="item in dropDownList.positionList" :value="item.id" :key="item.id">{{item.name}}</Option>
                    </Select>
                </Col>
                <Col span="3">
                    <Button icon="ios-search" @click="getEmpLists">搜索</Button>
                </Col>
            </Row>
            <br>
            <Row>
                <Table border ref="selection" :columns="empColumns" :data="empLists">
                    <template slot-scope="{ row, index }" slot="action">
                        <Button type="primary" style="margin-right: 5px" @click="selected(index)">选择</Button>
                    </template>
                </Table>
            </Row>
            <br>
            <Row type="flex" justify="center">
                <Page :total="empTotal" :page-size="pageSize" show-elevator show-total @on-change="empPageChange"  @on-page-size-change="onEmpPageSizeChange"/>
            </Row>
            <div slot="footer">
                <Button>返回</Button>
            </div>
        </Modal>
        <Modal
            v-model="updateModal"
            title="修改奖惩记录"
            width=30%
            @on-visible-change="cancel">
            
            <Form ref="rpList" :model="rpList" :rules="rpRules" :label-width="90" >
                <Row>
                    <Col span="12">
                        <FormItem label="姓名：">
                            {{rpList.name}}
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="工号：">
                            {{rpList.workId}}
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="所属部门：">
                            {{rpList.departmentName}}
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="职位：">
                            {{rpList.positionName}}
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="奖惩类型：" prop="type">
                            <RadioGroup v-model="rpList.type">
                                <Radio label="奖励">奖励</Radio>
                                <Radio label="惩罚">惩罚</Radio>
                            </RadioGroup>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="奖惩日期：" prop="date">
                            <DatePicker type="date" placeholder="选择奖惩日期" v-model="rpList.date"></DatePicker>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="奖惩分数：" prop="point">
                            <Input v-model="rpList.point" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="奖惩原因：" prop="reason">
                            <Input v-model="rpList.reason" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                        <FormItem label="备注：" prop="remark">
                            <Input v-model="rpList.remark" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
            <div slot="footer">
                <Button @click="handleReset('rpList')">重置</Button>
                <Button type="primary" @click="updateRp('rpList')">保存</Button>
            </div>
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
                    selectEmpModal: false,
                    updateModal: false,
                    page: 1,
                    empPage: 1,
                    total: 100,
                    empTotal: 100,
                    limit: 10,
                    empLimit: 5,
                    pageSize: 5,
                    dropDownList: [],
                    rpList: {
                        id: '',
                        type: '',
                        reason: '',
                        point: null,
                        date: '',
                        remark: '',
                        eid: '',
                        name: '',
                        positionName: '',
                        departmentName: '',
                        workId: '',
                    },
                    rpLists: [],
                    empList: {
                        id: '',
                        name: '',
                        workId: '',
                        departmentName: '',
                        positionName: '',
                    },
                    empLists: [],
                    souformItem:{
                        departmentId: null,
                        positionId: null,
                        jobLevelId: null,
                        name: '',
                    },
                    souformItem2:{
                        departmentId: null,
                        positionId: null,
                        jobLevelId: null,
                        name: '',
                    },
                    empColumns: [
                        {
                            title: '姓名',
                            key: 'name',
                        },
                        {
                            title: '工号',
                            key: 'workId'
                        },
                        {
                            title: '部门',
                            key: 'departmentName',
                        },
                        {
                            title: '职位',
                            key: 'positionName',
                        },
                        {
                            title: '职称',
                            key: 'jobLevelName',
                        },
                        {
                            title: '操作',
                            slot: 'action',
                            align: 'center'
                        }
                    ],
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
                    rpRules:{
                        eid: [
                            {required: true, message: '请选择员工', trigger: 'change'}
                        ],
                        type: [
                            {required: true, message: '类型不能为空', trigger: 'change'}
                        ],
                        date: [
                            {required: true, type: 'date', message: '奖惩日期不能为空', trigger: 'change'}
                        ],
                        point: [
                            {required: true, message: '奖惩分数不能为空', trigger: 'blur'}
                        ],
                        reason: [
                            {required: true, message: '奖惩原因不能为空', trigger: 'blur'}
                        ],
                        remark: [
                            {type: 'string', max: 50, message: '备注长度不能超过50个字符'}
                        ]
                    }
                }
            },
        methods: {
            onPageSizeChange(index){
                this.limit = index;
            },
            pageChange(index){
                this.page = index;
            },
            onEmpPageSizeChange(index){
                this.empLimit = index;
            },
            empPageChange(index){
                this.empPage = index;
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
                    name: this.souformItem.name,
                }).then(resp => {
                    this.rpLists = resp.data.data.list;
                    this.total = resp.data.data.total;
                })
            },
            addRp(name){
                this.$refs[name].validate((valid) => {
                    if(valid) {
                        this.postRequest("/rp/addRp",{
                            date: this.rpList.date,
                            eid: this.rpList.eid,
                            point: this.rpList.point,
                            reason: this.rpList.reason,
                            remark: this.rpList.remark,
                            type: this.rpList.type
                        }).then(resp=> {
                            if (resp.data.error == false && resp.data.code == 200) {
                                this.getRpList();
                                this.$Message.success(resp.data.data);
                                this.addModal = false;

                                //初始化字段
                                this.$refs[name].resetFields();

                                this.rpList.eid = "";
                                this.rpList.name = "";
                                this.rpList.workId = "";
                                this.rpList.departmentName = "";
                                this.rpList.positionName = ""
                            } else {
                                this.$Message.error(resp.data.message);
                            }
                        })
                    }
                })
            },
            selectEmp() {
                this.selectEmpModal = true;
                this.getEmpLists();
            },
            getEmpLists() {
                this.getRequest("/employee/allEmp",{
                    page: this.empPage,
                    limit: this.empLimit,
                    departmentId: this.souformItem2.departmentId,
                    jobLevelId: this.souformItem2.jobLevelId,
                    positionId: this.souformItem2.positionId,
                    name: this.souformItem2.name,
                }).then(resp=>{
                    this.empLists = resp.data.data.list;
                    this.empTotal = resp.data.data.total;
                })
            },
            selected(index) {
                this.rpList.eid = this.empLists[index].id;
                this.rpList.name = this.empLists[index].name;
                this.rpList.workId = this.empLists[index].workId;
                this.rpList.departmentName = this.empLists[index].departmentName;
                this.rpList.positionName = this.empLists[index].positionName;
                this.selectEmpModal = false;
            },
            beforeUpdate (index) {
                this.getRequest("/updateRp",null).then(resp=>{
                    if(resp.data.code != 403){
                        this.updateModal = true;
                        this.rpList.id = this.rpLists[index].id;
                        this.rpList.type = this.rpLists[index].type;
                        this.rpList.reason = this.rpLists[index].reason;
                        this.rpList.point = "" + this.rpLists[index].point;
                        this.rpList.date = this.rpLists[index].date;
                        this.rpList.remark = this.rpLists[index].remark;
                        this.rpList.eid = this.rpLists[index].eid;
                        this.rpList.name = this.rpLists[index].employee.name;
                        this.rpList.positionName = this.rpLists[index].employee.positionName;
                        this.rpList.departmentName = this.rpLists[index].employee.departmentName;
                        this.rpList.workId = this.rpLists[index].employee.workId;
                    }
                })
            },
            updateRp(name) {
                // if(this.rpList.type == "奖励"){
                //     if(this.rpList.point.substring(0,1) = "+"){
                //         this.rpList.point = "+" + this.rpList.point;
                //     }
                //     this.rpList.point = "+" + this.rpList.point;
                //     console.log(this.rpList.point);
                // }else{
                //     this.rpList.point = "-" + this.rpList.point;
                //     console.log(this.rpList.point);
                // }
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.putRequest("/rp/updateRp",{
                            id: this.rpList.id,
                            type: this.rpList.type,
                            reason: this.rpList.reason,
                            point: this.rpList.point,
                            date: this.rpList.date,
                            remark: this.rpList.remark,
                        }).then(resp => {
                            if (resp.data.code != 400) {
                                this.$Message.success(resp.data.data);
                                this.updateModal = false;
                                this.getRpList();

                                //初始化字段
                                this.$refs[name].resetFields();
                            } else {
                                this.$Message.error(resp.data.message);
                            }
                        })
                    }
                })
            },
            remove(id){
                this.getRequest("/deleteRp",null).then(resp=>{
                    if(resp.data.code != 403){
                        this.$Modal.confirm({
                        title: '你正在进行删除操作',
                        content: '<p>你确定要删除该奖惩记录吗?</p>',
                        onOk: () => {
                            var _this = this;
                            this.deleteRequest("/rp/deleteRp/" + id).then(resp=> {
                                if(resp.data.code != 400){
                                    this.$Message.success(resp.data.data);
                                    this.spinShow = false;
                                    _this.getRpList();
                                }else{
                                    this.$Message.error(resp.data.message);
                                }
                            })
                        },
                    });
                    }
                })
            },
            addhandleReset(name){
                this.rpList.eid = "";
                this.rpList.name = "";
                this.rpList.workId = "";
                this.rpList.departmentName = "";
                this.rpList.positionName = "";
                this.$refs[name].resetFields();
            },
            handleReset (name) {
                this.$refs[name].resetFields();
            },
            cancel(flag){
                if(flag == false){
                    this.$refs['rpList'].resetFields();
                    this.$refs['souformItem2'].resetFields();
                    this.empPage = 1,
                    this.rpList.eid = "";
                    this.rpList.name = "";
                    this.rpList.workId = "";
                    this.rpList.departmentName = "";
                    this.rpList.positionName = "";
                }
            },
            befaddRp(){
                this.getRequest("/addRp",null).then(resp=>{
                    if(resp.data.code != 403){
                        this.addModal = true;
                    }
                })
            }
        },
        mounted: function (){
            this.getDropDownList();
            this.getRpList();
        },
        watch: {
            page: "getRpList",
            limit: "getRpList",
            empPage: "getEmpLists",
            empLimit: "getEmpLists",
        },
    }
</script>

