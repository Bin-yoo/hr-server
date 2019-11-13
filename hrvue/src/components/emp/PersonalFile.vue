<template>
    <div>
        <Row>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>姓名：</p></Col>
                    <Col span="11"><p>choudidi</p></Col>
                </Row>
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>性别：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>身份证号码：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>政治面貌：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>籍贯：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>工号：</p></Col>
                    <Col span="11"><p>choudidi</p></Col>
                </Row>
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>民族：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>婚姻状态：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>出生日期：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>联系方式：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
            <Col span="2">
                <img :style="{border:'0.2px solid black',width:'120px',height:'150px',margin:'0 0 0 20px'}">
            </Col>
        </Row>
        <Row>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>邮箱：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>居住地址：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>学历：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
        </Row>
        <Row>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>毕业院校：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>专业：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
        </Row>
        <Divider :style="{margin: '0 0 24px 0'}" />
        <Row>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>所属部门：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>职位：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>职称：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
        </Row>
        <Row>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>就职日期：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>在职状态：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
        </Row>
        <Row>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>转正日期：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>是否正式员工：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
        </Row>
        <Row>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>合同起始日期：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>合同结束日期：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
            <Col span="4">
                <Row :style="{margin: '0 0 15px 0'}">
                    <Col span="9"><p>离职日期：</p></Col>
                    <Col span="11"><p></p></Col>
                </Row>
            </Col>
        </Row>
        <Row>
            <Col span="1">
                <Button type="primary" @click="beforeUpdate()">修改资料</Button>
            </Col>
        </Row>
        <br>
        <Row>
            <Tabs type="card">
                <TabPane label="奖惩资料">
                    <Table :columns="rewPunColumns" :data="empRpLists"></Table>
                    <Row type="flex" justify="center"  :style="{margin: '10px 0 0 0'}">
                        <Col  ><Page :total="rpTotal" :page-size="rpPageSize" show-elevator show-total @on-change="rpPageChange" @on-page-size-change="onRpPageSizeChange"/></Col>
                    </Row>
                </TabPane>
                <TabPane label="考核资料">
                    <Table :columns="assColumns" :data="assData"></Table>
                    <Row type="flex" justify="center" :style="{margin: '10px 0 0 0'}">
                        <Col><Page :total="1" show-elevator /></Col>
                    </Row>
                </TabPane>
            </Tabs>
        </Row>
        <Modal
            v-model="updateModal"
            title="编辑员工档案"
            width=55%
            @on-visible-change="cancel">

            <Form :label-width="100" >
                <Row>
                    <Col span="8">
                        <Row>
                            <FormItem label="姓名：" prop="name">
                                <Input v-model="perEmpList.name" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="性别：" prop="sex">
                                <RadioGroup v-model="perEmpList.sex">
                                    <Radio label="男">男</Radio>
                                    <Radio label="女">女</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="身份证号：" prop="idCard">
                                <Input v-model="perEmpList.idCard" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="政治面貌：" prop="politiclId">
                                <Select v-model="perEmpList.politiclId">
                                    <Option v-for="item in dropDownList.politicalStatusList" :value="item.id" :key="item.id"  :label="item.name"></Option>
                                </Select>
                            </FormItem>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row>
                            <FormItem label="民族：" prop="nationId">
                                <Select v-model="perEmpList.nationId" clearable>
                                    <Option v-for="item in dropDownList.nationList" :value="item.id" :key="item.id" :label="item.name"></Option>
                                </Select>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="婚姻状态：" prop="wedlock">
                                <RadioGroup v-model="perEmpList.wedlock">
                                    <Radio label="未婚">未婚</Radio>
                                    <Radio label="已婚">已婚</Radio>
                                    <Radio label="离异">离异</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="出生日期：" prop="birthday">
                                <DatePicker type="date" placeholder="选择出生日期" v-model="perEmpList.birthday"></DatePicker>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="联系方式：" prop="phone">
                                <Input v-model="perEmpList.phone" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                    </Col>
                    <Col span="4" offset="2">
                        <Row>
                            <img  :style="{border:'0.2px solid black',width:'128px',height:'166px'}">
                        </Row>
                        <Row>

                                <!-- 上传组件相关方法
                                :default-file-list="defaultList"
                                :on-success="handleSuccess"
                                :on-format-error="handleFormatError"
                                :on-exceeded-size="handleMaxSize"
                                :before-upload="handleBeforeUpload" -->

                            <Upload
                                ref="upload"
                                :show-upload-list="false"
                                :format="['jpg','jpeg','png']"
                                :max-size="2048"
                                multiple
                                type="drag"
                                action="//jsonplaceholder.typicode.com/posts/"
                                style="display: inline-block;width:128px;">

                                <Button icon="ios-cloud-upload-outline" :style="{width:'120px',border:'none'}">上传图片</Button>
                            </Upload>
                        </Row>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="籍贯：" prop="nativePlace">
                            <Input v-model="perEmpList.nativePlace" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="邮箱：" prop="email">
                            <Input v-model="perEmpList.email" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="居住地址：" prop="address">
                            <Input v-model="perEmpList.address" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="学历：" prop="titopDegree">
                            <Select v-model="perEmpList.titopDegree">
                                <Option value="小学">小学</Option>
                                <Option value="初中">初中</Option>
                                <Option value="高中">高中</Option>
                                <Option value="大专">大专</Option>
                                <Option value="本科">本科</Option>
                                <Option value="硕士">硕士</Option>
                                <Option value="博士">博士</Option>
                                <Option value="其他">其他</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="毕业院校：" prop="school">
                            <Input v-model="perEmpList.school" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="专业：" prop="specialty">
                            <Input v-model="perEmpList.specialty" placeholder="请输入"></Input>
                        </FormItem>
                    </Col> 
                </Row>
            </Form>
            <div slot="footer">
                <Button type="primary" @click="update('employee')">保存</Button>
            </div>
        </Modal>
    </div>
</template>

<script>
    import Treeselect from '@riophae/vue-treeselect'
    import '@riophae/vue-treeselect/dist/vue-treeselect.css'
    import moment from "moment"
    export default {
        components: { Treeselect },
        name: 'PerEmp',
        data() {
            return {
                updateModal: false,
                rpPage:1,
                rpTotal: 100,
                rpLimit: 3,
                rpPageSize: 5,
                dropDownList: [],
                rpByIdLists: [],
                perEmpList: {

                },
            }
        },
        methods: {
            onRpPageSizeChange(index){
                this.rpLimit = index;
            },
            rpPageChange(index){
                this.rpPage = index;
            },
            getDropDownList(){
                this.getRequest("/employee/init").then(resp=> {
                    this.dropDownList = resp.data.data;
                })
            },
            beforeUpdate() {
                this.updateModal = true;
            },
        },
        mounted: function (){
            this.getDropDownList();
        },
        watch: {
            
        },
    }
</script>

<style>
    p{
        float: left;
    }
    .departDown{
        height: 30px;
    }
</style>