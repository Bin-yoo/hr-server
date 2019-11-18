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
                        <Button icon="ios-search" @click="getEmployeeList">搜索</Button>
                    </Col>
                </Row>
            </Col>
            <Col span="2"><Button type="primary" @click="addModal = true">添加档案</Button></Col>
        </Row>
        <br>
        <Row>
            <Table border ref="selection" :columns="columns" :data="employees">
                <template slot-scope="{ row, index }" slot="action">
                    <Button style="margin-right: 5px" @click="show(index)">查看</Button>
                    <Button type="primary" style="margin-right: 5px" @click="beforeUpdate(index)">编辑</Button>
                    <Button type="error" @click="remove(row.id)">删除</Button>
                </template>
            </Table>
        </Row>
        <Row :style="{margin: '20px 0 0 0'}">
            <Col span="1"><Button type="error">批量删除</Button></Col>
            <Row :style="{textAlign: 'center'}">
                <Page :total="total" show-sizer show-elevator show-total @on-change="pageChange" @on-page-size-change="onPageSizeChange"/>
            </Row>
        </Row>
        <Modal
            v-model="addModal"
            title="添加员工档案"
            width=55%
            @on-visible-change="cancel">

            <Form ref="newEmployee" :model="newEmployee" :rules="newEmployeeRules" :label-width="100">
                <Row>
                    <Col span="8">
                        <Row>
                            <FormItem label="姓名：" prop="name">
                                <Input v-model="newEmployee.name" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="性别：" prop="sex">
                                <RadioGroup v-model="newEmployee.sex">
                                    <Radio label="男">男</Radio>
                                    <Radio label="女">女</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="身份证号：" prop="idCard">
                                <Input v-model="newEmployee.idCard" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="政治面貌：" prop="politiclId" >
                                <Select v-model="newEmployee.politiclId" clearable>
                                    <Option v-for="item in dropDownList.politicalStatusList" :value="item.id" :key="item.id"  :label="item.name"></Option>
                                </Select>
                            </FormItem>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row>
                            <FormItem label="民族：" prop="nationId">
                                <Select v-model="newEmployee.nationId" clearable>
                                    <Option v-for="item in dropDownList.nationList" :value="item.id" :key="item.id" :label="item.name"></Option>
                                </Select>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="婚姻状态：" prop="wedlock">
                                <RadioGroup v-model="newEmployee.wedlock">
                                    <Radio label="未婚">未婚</Radio>
                                    <Radio label="已婚">已婚</Radio>
                                    <Radio label="离异">离异</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="出生日期：" prop="birthday">
                                <DatePicker type="date" placeholder="请选择生日" v-model="newEmployee.birthday"></DatePicker>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="联系方式：" prop="phone">
                                <Input v-model="newEmployee.phone" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                    </Col>
                    <Col span="4" offset="2">
                        <Row>
                            <img src="../../assets/avatar.png" :style="{border:'0.2px solid black',width:'128px',height:'166px'}" v-if="newEmployee.picture=='' || newEmployee.picture==null ? true : false">
                            <img :src="newEmployee.picture" :style="{border:'0.2px solid black',width:'128px',height:'166px'}" v-else>
                        </Row>
                        <Row>
                            <Upload
                                ref="upload"
                                name='picture'
                                :show-upload-list="false"
                                :format="['jpg','jpeg','png']"
                                :max-size="2048"
                                :on-success="uploadNewSuccess"
                                multiple
                                type="drag"
                                action="http://111.230.141.100:8080/hrserver/employee/picture"
                                style="display: inline-block;width:128px;">
                                <Button icon="ios-cloud-upload-outline" :style="{width:'120px',border:'none'}">上传图片</Button>
                            </Upload>
                        </Row>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="籍贯：" prop="nativePlace">
                            <Input v-model="newEmployee.nativePlace" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="邮箱：" prop="email">
                            <Input v-model="newEmployee.email" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="居住地址：" prop="address">
                            <Input v-model="newEmployee.address" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="学历：" prop="titopDegree">
                            <Select v-model="newEmployee.titopDegree" clearable>
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
                            <Input v-model="newEmployee.school" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="专业：" prop="specialty">
                            <Input v-model="newEmployee.specialty" placeholder="请输入"></Input>
                        </FormItem>
                    </Col> 
                </Row>
                <Divider :style="{margin: '0 0 24px 0'}" />
                <Row>
                    <Col span="8">
                        <FormItem label="所属部门：" prop="departmentId">
                            <treeselect v-model="newEmployee.departmentId" :options="dropDownList.departmentList" :default-expand-level="1" placeholder="请选择部门"/>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="职称：" prop="jobLevelId">
                            <Select v-model="newEmployee.jobLevelId" clearable>
                                <Option v-for="item in dropDownList.jobLevelList" :value="item.id" :key="item.id" :label="item.name"></Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="职位：" prop="positionId">
                            <Select v-model="newEmployee.positionId" clearable>
                                <Option v-for="item in dropDownList.positionList" :value="item.id" :key="item.id" :label="item.name"></Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="就职日期：" prop="beginDate">
                            <DatePicker type="date" placeholder="请选择就职日期" format="yyyy-MM-dd" v-model="newEmployee.beginDate"></DatePicker>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="就职状态：" prop="workState">
                            <RadioGroup v-model="newEmployee.workState">
                                <Radio label="在职">在职</Radio>
                                <Radio label="离职">离职</Radio>
                            </RadioGroup>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="转正日期：" prop="conversionTime">
                            <DatePicker type="date" placeholder="请选择转正日期" format="yyyy-MM-dd" v-model="newEmployee.conversionTime"></DatePicker>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="基本工资：" prop="baseSalary">
                            <Input v-model="newEmployee.baseSalary" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="合同起始日期：" prop="beginContract">
                            <DatePicker type="date" placeholder="请选择合同起始日期" format="yyyy-MM-dd" v-model="newEmployee.beginContract"></DatePicker>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="合同结束日期：" prop="endContract">
                            <DatePicker type="date" placeholder="请选择合同结束日期" format="yyyy-MM-dd" v-model="newEmployee.endContract"></DatePicker>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="工号：" prop="workId">
                            <Input v-model="newEmployee.workId" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
            <div slot="footer">
                <Button @click="handleReset('newEmployee')">重置</Button>
                <Button type="primary" @click="addEmployee('newEmployee')">保存</Button>
            </div>
        </Modal>
        <Modal
            v-model="updateModal"
            title="编辑员工档案"
            width=55%
            @on-visible-change="cancel">

            <Form ref="employee" :model="employee" :rules="newEmployeeRules" :label-width="100" >
                <Row>
                    <Col span="8">
                        <Row>
                            <FormItem label="姓名：" prop="name">
                                <Input v-model="employee.name" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="性别：" prop="sex">
                                <RadioGroup v-model="employee.sex">
                                    <Radio label="男">男</Radio>
                                    <Radio label="女">女</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="身份证号：" prop="idCard">
                                <Input v-model="employee.idCard" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="政治面貌：" prop="politiclId">
                                <Select v-model="employee.politiclId">
                                    <Option v-for="item in dropDownList.politicalStatusList" :value="item.id" :key="item.id"  :label="item.name"></Option>
                                </Select>
                            </FormItem>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row>
                            <FormItem label="民族：" prop="nationId">
                                <Select v-model="employee.nationId" clearable>
                                    <Option v-for="item in dropDownList.nationList" :value="item.id" :key="item.id" :label="item.name"></Option>
                                </Select>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="婚姻状态：" prop="wedlock">
                                <RadioGroup v-model="employee.wedlock">
                                    <Radio label="未婚">未婚</Radio>
                                    <Radio label="已婚">已婚</Radio>
                                    <Radio label="离异">离异</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="出生日期：" prop="birthday">
                                <DatePicker type="date" placeholder="选择出生日期" v-model="employee.birthday"></DatePicker>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="联系方式：" prop="phone">
                                <Input v-model="employee.phone" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                    </Col>
                    <Col span="4" offset="2">
                        <Row>
                            <img src="../../assets/avatar.png" :style="{border:'0.2px solid black',width:'128px',height:'166px'}" v-if="employee.picture=='' || employee.picture==null ? true : false">
                            <img :src="employee.picture" :style="{border:'0.2px solid black',width:'128px',height:'166px'}" v-else>
                        </Row>
                        <Row>
                            <Upload
                                ref="upload"
                                name='picture'
                                :show-upload-list="false"
                                :format="['jpg','jpeg','png']"
                                :max-size="2048"
                                :on-success="uploadSuccess"
                                multiple
                                type="drag"
                                action="http://111.230.141.100:8080/hrserver/employee/picture"
                                style="display: inline-block;width:128px;">
                                <Button icon="ios-cloud-upload-outline" :style="{width:'120px',border:'none'}">上传图片</Button>
                            </Upload>
                        </Row>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="籍贯：" prop="nativePlace">
                            <Input v-model="employee.nativePlace" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="邮箱：" prop="email">
                            <Input v-model="employee.email" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="居住地址：" prop="address">
                            <Input v-model="employee.address" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="学历：" prop="titopDegree">
                            <Select v-model="employee.titopDegree">
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
                            <Input v-model="employee.school" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="专业：" prop="specialty">
                            <Input v-model="employee.specialty" placeholder="请输入"></Input>
                        </FormItem>
                    </Col> 
                </Row>
                <Divider :style="{margin: '0 0 24px 0'}" />
                <Row>
                    <Col span="8">
                        <FormItem label="所属部门：" prop="departmentId">
                            <treeselect v-model="employee.departmentId" :options="dropDownList.departmentList" :default-expand-level="1" placeholder="请选择部门"/>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="职称：" prop="jobLevelId">
                            <Select v-model="employee.jobLevelId">
                                <Option v-for="item in dropDownList.jobLevelList" :value="item.id" :key="item.id" :label="item.name"></Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="职位：" prop="positionId">
                            <Select v-model="employee.positionId">
                                <Option v-for="item in dropDownList.positionList" :value="item.id" :key="item.id" :label="item.name"></Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="就职日期：" prop="beginDate">
                            <DatePicker type="date" placeholder="请选择就职日期" v-model="employee.beginDate"></DatePicker>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="就职状态：" prop="workState">
                            <RadioGroup v-model="employee.workState">
                                <Radio label="在职">在职</Radio>
                                <Radio label="离职">离职</Radio>
                            </RadioGroup>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="转正日期：" prop="conversionTime">
                            <DatePicker type="date" placeholder="请选择转正日期" v-model="employee.conversionTime"></DatePicker>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="基本工资：" prop="baseSalary">
                            <Input v-model="employee.baseSalary" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="合同起始日期：" prop="beginContract">
                            <DatePicker type="date" placeholder="请选择合同起始日期" format="yyyy-MM-dd" v-model="employee.beginContract"></DatePicker>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="合同结束日期：" prop="endContract">
                            <DatePicker type="date" placeholder="请选择合同结束日期" format="yyyy-MM-dd" v-model="employee.endContract"></DatePicker>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="离职日期：" prop="quitTime">
                            <DatePicker type="date" placeholder="请选择离职日期" format="yyyy-MM-dd" v-model="employee.quitTime"></DatePicker>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
            <div slot="footer">
                <Button @click="handleReset('employee')">重置</Button>
                <Button type="primary" @click="update('employee')">保存</Button>
            </div>
        </Modal>
        <div v-if="showModal">
            <Modal
                v-model="showModal"
                title="员工档案详情"
                width=40%>

                <Row>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>姓名：</p></Col>
                            <Col span="11"><p>{{employees[index].name}}</p></Col>
                        </Row>
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>性别：</p></Col>
                            <Col span="11"><p>{{employees[index].sex}}</p></Col>
                        </Row>
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>身份证号码：</p></Col>
                            <Col span="11"><p>{{employees[index].idCard}}</p></Col>
                        </Row>
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>政治面貌：</p></Col>
                            <Col span="11"><p>{{employees[index].politiclName}}</p></Col>
                        </Row>
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>籍贯：</p></Col>
                            <Col span="11"><p>{{employees[index].nativePlace}}</p></Col>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>工号：</p></Col>
                            <Col span="11"><p>{{employees[index].workId}}</p></Col>
                        </Row>
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>民族：</p></Col>
                            <Col span="11"><p>{{employees[index].nationName}}</p></Col>
                        </Row>
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>婚姻状态：</p></Col>
                            <Col span="11"><p>{{employees[index].wedlock}}</p></Col>
                        </Row>
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>出生日期：</p></Col>
                            <Col span="11"><p>{{employees[index].birthday}}</p></Col>
                        </Row>
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>联系方式：</p></Col>
                            <Col span="11"><p>{{employees[index].phone}}</p></Col>
                        </Row>
                    </Col>
                    <Col span="5">
                        <img :src="employees[index].picture" :style="{border:'0.2px solid black',width:'120px',height:'150px',margin:'0 0 0 20px'}">
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>邮箱：</p></Col>
                            <Col span="11"><p>{{employees[index].email}}</p></Col>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>居住地址：</p></Col>
                            <Col span="11"><p>{{employees[index].address}}</p></Col>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>学历：</p></Col>
                            <Col span="11"><p>{{employees[index].titopDegree}}</p></Col>
                        </Row>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>毕业院校：</p></Col>
                            <Col span="11"><p>{{employees[index].school}}</p></Col>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>专业：</p></Col>
                            <Col span="11"><p>{{employees[index].specialty}}</p></Col>
                        </Row>
                    </Col>
                </Row>
                <Divider :style="{margin: '0 0 24px 0'}" />
                <Row>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>所属部门：</p></Col>
                            <Col span="11"><p>{{employees[index].departmentName}}</p></Col>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>职位：</p></Col>
                            <Col span="11"><p>{{employees[index].positionName}}</p></Col>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>职称：</p></Col>
                            <Col span="11"><p>{{employees[index].jobLevelName}}</p></Col>
                        </Row>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>就职日期：</p></Col>
                            <Col span="11"><p>{{employees[index].beginDate}}</p></Col>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>在职状态：</p></Col>
                            <Col span="11"><p>{{employees[index].workState}}</p></Col>
                        </Row>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>转正日期：</p></Col>
                            <Col span="11"><p>{{employees[index].conversionTime}}</p></Col>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>是否正式员工：</p></Col>
                            <Col span="11"><p>{{employees[index].conversionTime == null||'' ? "实习" : "正式"}}</p></Col>
                        </Row>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>合同起始日期：</p></Col>
                            <Col span="11"><p>{{employees[index].beginContract}}</p></Col>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>合同结束日期：</p></Col>
                            <Col span="11"><p>{{employees[index].endContract}}</p></Col>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row :style="{margin: '0 0 15px 0'}">
                            <Col span="9"><p>离职日期：</p></Col>
                            <Col span="11"><p>{{employees[index].quitTime}}</p></Col>
                        </Row>
                    </Col>
                </Row>
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
            </Modal>
        </div>
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
                addModal: false,
                updateModal: false,
                showModal: false,
                page:1,
                total: 100,
                limit: 10,
                rpPage:1,
                rpTotal: 100,
                rpLimit: 3,
                rpPageSize: 3,
                loading: false,
                index: 1,
                empRpLists: [],
                dropDownList: [],
                department:{
                    id: null,
                    name: '',
                    parentId: null
                },
                employee: {
                    id: '',
                    workId: '',
                    name: '',
                    sex: '',
                    nationId: '',
                    nationName: '',
                    birthday: '',
                    politiclId: '',
                    politiclName: '',
                    picture: '',
                    wedlock: '',
                    nativePlace: '',
                    idCard: '',
                    email: '',
                    phone: '',
                    address: '',
                    departmentId: '',
                    departmentName: '',
                    positionId: '',
                    positionName: '',
                    jobLevelId: '',
                    jobLevelName: '',
                    titopDegree: '',
                    specialty: '',
                    school: '',
                    beginDate: '',
                    workState: '',
                    conversionTime: '',
                    quitTime: '',
                    beginContract: '',
                    endContract: '',
                    baseSalary: '',
                },
                employees: [],
                formatDate:{   //转化后日期临时存储
                    birthday:'',
                    beginDate:'',
                    conversionTime:'',
                    beginContract:'',
                    endContract:'',
                    quitTime:'',
                },
                newEmployee: {
                    name: '',           //名字
                    nationId: '',       //民族
                    sex: '',            //性别
                    wedlock: '',        //婚姻状态
                    idCard: '',         //身份证号
                    birthday: '',       //出生日期
                    politiclId: '',     //政治面貌
                    phone: '',          //联系方式
                    nativePlace: '',    //籍贯地
                    email: '',          //邮箱
                    address: '',        //居住地址
                    departmentId: null, //所属部门
                    jobLevelId: '',     //职称
                    positionId: '',     //职位
                    titopDegree: '',    //学历
                    school: '',         //毕业学院
                    specialty: '',      //专业
                    beginDate: '',      //就职日期
                    workState: '',      //就职状态（在职or离职）
                    conversionTime: '', //转正日期
                    baseSalary: '',     //基本工资
                    picture: '',        //照片
                    beginContract: '',  //合同起始日期
                    endContract: '',    //合同结束日期
                    quitTime: '',       //离职日期
                    workId:'',           //工号
                },
                newEmployeeRules: {
                    name: [
                        {required: true, message: '姓名不能为空', trigger: 'blur'}
                    ],
                    sex: [
                        {required: true, message: '请选择性别', trigger: 'change'}
                    ],
                    idCard: [
                        {required: true, message: '身份证不能为空', trigger: 'blur'},
                        {type: 'string', min: 16, message: '身份证不能少于16位'},
                        {type: 'string', max: 18, message: '身份证不能大于18位'},
                    ],
                    politiclId: [
                        {required: true, type: 'number', message: '请选择政治面貌', trigger: 'change'}
                    ],
                    wedlock: [
                        {required: true, message: '请选择婚姻状态', trigger: 'change'}
                    ],
                    nationId: [
                        {required: true, type: 'number', message: '请选择民族', trigger: 'change'}
                    ],
                    birthday: [
                        {required: true, type: 'date', message: '请选择出生日期', trigger: 'change'}
                    ],
                    phone: [
                        {required: true, message: '联系方式不能为空', trigger: 'blur'}
                    ],
                    departmentId: [
                        {required: true, type: 'number', message: '请选择所属部门', trigger: 'change'}
                    ],
                    jobLevelId: [
                        {required: true, type: 'number', message: '请选择职称', trigger: 'change'}
                    ],
                    positionId: [
                        {required: true, type: 'number', message: '请选择职位', trigger: 'change'}
                    ],
                    titopDegree: [
                        {required: true, message: '请选择学历', trigger: 'change'}
                    ],
                    school: [
                        {required: true, message: '毕业院校不能为空', trigger: 'blur'}
                    ],
                    specialty: [
                        {required: true, message: '专业不能为空', trigger: 'blur'}
                    ],
                    beginDate: [
                        {required: true, type: 'date', message: '请选择就职日期', trigger: 'change'}
                    ],
                    workState: [
                        {required: true, message: '请选择就职状态', trigger: 'change'}
                    ],
                    beginContract: [
                        {required: true, type: 'date', message: '请选择合同起始日期', trigger: 'change'}
                    ],
                    endContract: [
                        {required: true, type: 'date', message: '请选择合同结束日期', trigger: 'change'}
                    ],
                    baseSalary: [
                        {required: true, message: '基本工资不能为空', trigger: 'blur'},
                        {message: '基本工资只能用数字', pattern:/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/}
                    ],
                    workId: [
                        {required: true, message: '工号不能为空', trigger: 'blur'},
                        {message: '工号只能用纯数字', pattern: /^[0-9]*$/}
                    ],
                },
                pictureItem:{
                    name: '',
                    url: '',
                },
                souformItem:{
                    departmentId: null,
                    positionId: null,
                    jobLevelId: null,
                    name: '',
                },
                formItem:{
                    name: '',       //名字
                    nationID:'',    //民族
                    gender:'',      //性别
                    politicID:'',   //政治面貌
                    nativePlace:'', //籍贯地
                    birthday:'',    //出生日期
                    idCard:'',      //身份证号
                    wedlock:'',     //婚姻状态
                    phone:'',       //联系方式
                    email:'',       //邮箱
                    adress:'',      //居住地址
                    departmentID:'',//所属部门
                    jobLevelID:'',  //职称
                    posID:'',       //职位
                    titopDegree:'', //学历
                    school:'',      //毕业学院
                    specialty:'',   //专业
                    beginDate:'',   //就职日期
                    workState:'',   //就职状态（在职or离职）
                    conversionTime:'',//转正日期
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
                        width: 90
                    },
                    {
                        title: '工号',
                        key: 'workId'
                    },
                    {
                        title: '部门',
                        key: 'departmentName',
                        width: 90
                    },
                    {
                        title: '职位',
                        key: 'positionName',
                        width: 90
                    },
                    {
                        title: '职称',
                        key: 'jobLevelName',
                        width: 110
                    },
                    {
                        title: '性别',
                        key: 'sex',
                        width: 65
                    },
                    {
                        title: '出生日期',
                        key: 'birthday'
                    },
                    {
                        title: '籍贯',
                        key: 'nativePlace'
                    },
                    {
                        title: '联系方式',
                        key: 'phone'
                    },
                    {
                        title: '电子邮箱',
                        key: 'email'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 230,
                        align: 'center'
                    }
                ],
                rewPunColumns: [
                    {
                        title: '奖惩日期',
                        key: 'date'
                    },
                    {
                        title: '奖惩类型',
                        key: 'type'
                    },
                    {
                        title: '奖惩分数',
                        key: 'point'
                    },
                    {
                        title: '奖惩原因',
                        key: 'reason'
                    },
                    {
                        title: '备注',
                        key: 'remark'
                    },
                ],
                rewPunData: [
                    {
                        date: "2019年10月21日",
                        type: "惩罚",
                        grade: "-1",
                        reason: "迟到",
                        remark: "迟到",
                    },
                ],
                assColumns: [
                    {
                        title: '考核日期',
                        key: 'date'
                    },
                    {
                        title: '考核内容',
                        key: 'content'
                    },
                    {
                        title: '考核结果',
                        key: 'result'
                    },
                    {
                        title: '备注',
                        key: 'remark'
                    },
                ],
                assData: [
                    {
                        date: "2019年10月21日",
                        content: "2019年第一季度考核",
                        result: "完成",
                        remark: "完成",
                    },
                ],
                transferColumns: [
                    {
                        title: '调动日期',
                        key: 'date'
                    },
                    {
                        title: '调前部门',
                        key: 'beforeDepar'
                    },
                    {
                        title: '调前职位',
                        key: 'beforePos'
                    },
                    {
                        title: '调动原因',
                        key: 'reason'
                    },
                    {
                        title: '调后部门',
                        key: 'afterDepar'
                    },
                    {
                        title: '调后职位',
                        key: 'afterPos'
                    },
                    {
                        title: '备注',
                        key: 'remark'
                    },
                ],
                transferdata: [
                    {
                        date: "2019年10月21日",
                        beforeDepar: "财务部",
                        beforePos: "财务经理",
                        reason: "工作需要",
                        afterDepar: "技术部",
                        afterPos: "项目经理",
                        remark: "项目需要",
                    },
                ],
            }
        },
        methods: {
            onPageSizeChange(index){
                this.limit = index;
            },
            pageChange(index){
                this.page = index;
            },
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
            getEmployeeList() {
                this.getRequest("/employee/allEmp",{
                    page: this.page,
                    limit: this.limit,
                    departmentId: this.souformItem.departmentId,
                    jobLevelId: this.souformItem.jobLevelId,
                    positionId: this.souformItem.positionId,
                    name: this.souformItem.name,
                }).then(resp=>{
                    this.employees = resp.data.data.list;
                    this.total = resp.data.data.total;
                })
            },
            getEmpRp(){
                var id = this.employees[this.index].id;
                this.getRequest("/rp/empRp/" + id,{
                    page: this.rpPage,
                    limit: this.rpLimit,
                }).then(resp=>{
                    this.empRpLists = resp.data.data.list;
                    this.rpTotal = resp.data.data.total;
                })
            },
            handleReset (name) {
                this.$refs[name].resetFields();
            },
            addEmployee(name) {
                if(this.newEmployee.birthday != ''){
                    this.formatDate.birthday = moment(this.newEmployee.birthday).format('YYYY-MM-DD');
                }
                if(this.newEmployee.beginDate != ''){
                    this.formatDate.beginDate = moment(this.newEmployee.beginDate).format('YYYY-MM-DD');
                }
                if(this.newEmployee.conversionTime != ''){
                    this.formatDate.conversionTime = moment(this.newEmployee.conversionTime).format('YYYY-MM-DD');
                }
                if(this.newEmployee.beginContract != ''){
                    this.formatDate.beginContract = moment(this.newEmployee.beginContract).format('YYYY-MM-DD');
                }
                if(this.newEmployee.endContract != ''){
                    this.formatDate.endContract = moment(this.newEmployee.endContract).format('YYYY-MM-DD');
                }
                if(this.newEmployee.quitTime != ''){
                    this.formatDate.quitTime = moment(this.newEmployee.quitTime).format('YYYY-MM-DD');
                }
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.postRequest("/employee/addEmp",{
                            name: this.newEmployee.name,
                            nationId: this.newEmployee.nationId,
                            sex: this.newEmployee.sex,
                            wedlock: this.newEmployee.wedlock,
                            idCard: this.newEmployee.idCard,
                            birthday: this.formatDate.birthday,
                            politiclId: this.newEmployee.politiclId,
                            phone: this.newEmployee.phone,
                            nativePlace: this.newEmployee.nativePlace,
                            email: this.newEmployee.email,
                            address: this.newEmployee.address,
                            departmentId: this.newEmployee.departmentId,
                            jobLevelId: this.newEmployee.jobLevelId,
                            positionId: this.newEmployee.positionId,
                            titopDegree: this.newEmployee.titopDegree,
                            school: this.newEmployee.school,
                            specialty: this.newEmployee.specialty,
                            beginDate: this.formatDate.beginDate,
                            workState: this.newEmployee.workState,
                            conversionTime: this.formatDate.conversionTime,
                            quitTime: this.formatDate.quitTime,
                            beginContract: this.formatDate.beginContract,
                            endContract: this.formatDate.endContract,
                            baseSalary: this.newEmployee.baseSalary,
                            workId: this.newEmployee.workId,
                            picture: this.newEmployee.picture
                        }).then(resp=> {
                            if (resp.data.code != 400) {
                                this.$Message.success(resp.data.data);
                                this.addModal = false;
                                this.getEmployeeList();
                                this.$refs.upload.clearFiles();

                                // //初始化字段
                                // this.$refs[name].resetFields();
                                //
                                // //初始化
                                // this.formatDate.birthday = '';
                                // this.formatDate.beginDate = '';
                                // this.formatDate.conversionTime = '';
                                // this.formatDate.beginContract = '';
                                // this.formatDate.endContract = '';
                                // this.formatDate.quitTime = '';
                            } else {
                                this.$Message.error(resp.data.message);
                            }
                        })
                    } else {
                        this.$Message.error('必填项不能为空');
                    }
                })
            },
            beforeUpdate(index){
                this.updateModal = true;
                this.employee.id = this.employees[index].id;
                this.employee.workId = '' + this.employees[index].workId;
                this.employee.name = this.employees[index].name;
                this.employee.sex = this.employees[index].sex;
                this.employee.nationId = this.employees[index].nationId;
                this.employee.nationName = this.employees[index].nationName;
                this.employee.birthday = this.employees[index].birthday;
                this.employee.politiclId = this.employees[index].politiclId;
                this.employee.politiclName = this.employees[index].politiclName;
                this.employee.wedlock = this.employees[index].wedlock;
                this.employee.nativePlace = this.employees[index].nativePlace;
                this.employee.idCard = this.employees[index].idCard;
                this.employee.email = this.employees[index].email;
                this.employee.phone = this.employees[index].phone;
                this.employee.address = this.employees[index].address;
                this.employee.departmentId = this.employees[index].departmentId;
                this.employee.departmentName = this.employees[index].departmentName;
                this.employee.positionId = this.employees[index].positionId;
                this.employee.positionName = this.employees[index].positionName;
                this.employee.jobLevelId = this.employees[index].jobLevelId;
                this.employee.jobLevelName = this.employees[index].jobLevelName;
                this.employee.titopDegree = this.employees[index].titopDegree;
                this.employee.specialty = this.employees[index].specialty;
                this.employee.school = this.employees[index].school;
                this.employee.beginDate = this.employees[index].beginDate;
                this.employee.workState = this.employees[index].workState;
                this.employee.conversionTime = this.employees[index].conversionTime;
                this.employee.quitTime = this.employees[index].quitTime;
                this.employee.beginContract = this.employees[index].beginContract;
                this.employee.endContract = this.employees[index].endContract;
                this.employee.baseSalary = '' + this.employees[index].baseSalary;
                this.employee.picture = this.employees[index].picture;
            },
            update (name) {
                if(this.employee.birthday != ''){
                    console.log(this.employee.birthday);
                    this.formatDate.birthday = moment(this.employee.birthday).format('YYYY-MM-DD');
                    console.log(this.formatDate.birthday);
                }
                if(this.employee.beginDate != ''){
                    this.formatDate.beginDate = moment(this.employee.beginDate).format('YYYY-MM-DD');
                }
                if(this.employee.conversionTime != ''){
                    this.formatDate.conversionTime = moment(this.employee.conversionTime).format('YYYY-MM-DD');
                }
                if(this.employee.beginContract != ''){
                    this.formatDate.beginContract = moment(this.employee.beginContract).format('YYYY-MM-DD');
                }
                if(this.employee.endContract != ''){
                    this.formatDate.endContract = moment(this.employee.endContract).format('YYYY-MM-DD');
                }
                if(this.employee.quitTime != ''){
                    this.formatDate.quitTime = moment(this.employee.quitTime).format('YYYY-MM-DD');
                }
                this.$refs[name].validate((valid) =>{
                    if (valid) {
                        this.putRequest("/employee/updateEmp",{
                            id: this.employee.id,
                            name: this.employee.name,
                            nationId: this.employee.nationId,
                            sex: this.employee.sex,
                            wedlock: this.employee.wedlock,
                            idCard: this.employee.idCard,
                            birthday: this.formatDate.birthday,
                            politiclId: this.employee.politiclId,
                            phone: this.employee.phone,
                            nativePlace: this.employee.nativePlace,
                            email: this.employee.email,
                            address: this.employee.address,
                            departmentId: this.employee.departmentId,
                            jobLevelId: this.employee.jobLevelId,
                            positionId: this.employee.positionId,
                            titopDegree: this.employee.titopDegree,
                            school: this.employee.school,
                            specialty: this.employee.specialty,
                            beginDate: this.formatDate.beginDate,
                            workState: this.employee.workState,
                            conversionTime: this.formatDate.conversionTime,
                            quitTime: this.formatDate.quitTime,
                            beginContract: this.formatDate.beginContract,
                            endContract: this.formatDate.endContract,
                            baseSalary: this.employee.baseSalary,
                            workId: this.employee.workId,
                            picture: this.employee.picture
                        }).then(resp => {
                            if (resp.data.code != 400) {
                                this.$Message.success(resp.data.data);
                                this.updateModal = false;
                                this.getEmployeeList();

                                //初始化字段
                                this.$refs[name].resetFields();
                                this.$refs.upload.clearFiles();
                                //初始化
                                this.formatDate.birthday = '';
                                this.formatDate.beginDate = '';
                                this.formatDate.conversionTime = '';
                                this.formatDate.beginContract = '';
                                this.formatDate.endContract = '';
                                this.formatDate.quitTime = '';
                            } else {
                                this.$Message.error(resp.data.message);
                            }
                        })
                    } else {
                        this.$Message.error('必填项不能为空');
                    }
                })
            },
            remove(id){
                this.$Modal.confirm({
                    title: '你正在进行删除操作',
                    content: '<p>你确定要删除该员工档案吗?</p>',
                    onOk: () => {
                        console.log(id);
                        var _this = this;
                        this.deleteRequest("/employee/deleteEmp/" + id).then(resp=> {
                            if(resp.data.code != 400){
                                this.$Message.success(resp.data.data);
                                this.spinShow = false;
                                _this.getEmployeeList();
                            }else{
                                this.$Message.error(resp.data.message);
                            }
                        })
                    },
                })
            },
            show(index) {
                this.showModal = true;
                this.index = index;
                this.getEmpRp();
            },
            cancel(flag){
                if(flag == false){
                    this.$refs['newEmployee'].resetFields();
                    this.$refs['employee'].resetFields();
                }
            },
            uploadNewSuccess(resp, file){
                console.log(resp)
                this.newEmployee.picture = resp.data;
                console.log(this.newEmployee.picture)
            },
            uploadSuccess(resp, file){
                this.employee.picture = resp.data;
            }
        },
        mounted: function (){
            this.getEmployeeList();
            this.getDropDownList();
        },
        watch: {
            page: "getEmployeeList",
            limit: "getEmployeeList",
            rpPage: "getEmpRp",
        },
    }
</script>

<style>

    .departDown{
        height: 30px;
    }

</style>