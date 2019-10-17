<template>
    <div>
        <Row><Col span="2"><h1>员工资料</h1></Col></Row>
        <Row :style="{margin: '15px 0 0 0'}">
            <Col span="3"><Input suffix="ios-search" placeholder="请输入..." style="width: auto" /></Col>
            <Col span="1"><Button icon="ios-search">搜索</Button></Col>
            <Col span="1" offset="18"><Button type="primary">添加</Button></Col>
        </Row>
        <Row :style="{margin: '20px 0 0 0'}">
            <Table border ref="selection" :columns="columns" :data="data1">
                <template slot-scope="{ row, index }" slot="action">
                    <Button type="primary" size="small" style="margin-right: 5px" @click="show(index)">编辑</Button>
                    <Button type="error" size="small" @click="remove(index)">删除</Button>
                </template>
            </Table>
        </Row>
        <Row :style="{margin: '20px 0 0 0'}">
            <Col span="1"><Button type="error">批量删除</Button></Col>
            <Page :total="100" show-elevator />
        </Row>
    </div>
</template>

<script>
export default {
    name: 'PerEmp',
    data() {
        return {
            columns: [
                {
                    type: 'selection',
                    width: 60,
                    align: 'center'
                },
                {
                    title: '姓名',
                    key: 'name'
                },
                {
                    title: '工号',
                    key: 'jobNum'
                },
                {
                    title: '部门',
                    key: 'department'
                },
                {
                    title: '职位',
                    key: 'position'
                },
                {
                    title: '性别',
                    key: 'sex'
                },
                {
                    title: '出生日期',
                    key: 'date'
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
                    width: 150,
                    align: 'center'
                }
            ],
            data1: [
                {
                    name: '宇哥',
                    jobNum: 20191016001,
                    department: "人事部",
                    position: "人事部经理",
                    sex: "男",
                    date: "2019年10月16日",
                    nativePlace: "广西",
                    phone: "12345678910",
                    email: "12345678@qq.com",
                },
                {
                    name: '宇哥',
                    jobNum: 20191016001,
                    department: "人事部",
                    position: "人事部经理",
                    sex: "男",
                    date: "2019年10月16日",
                    nativePlace: "广西",
                    phone: "12345678910",
                    email: "12345678@qq.com",
                },
                {
                    name: '宇哥',
                    jobNum: 20191016001,
                    department: "人事部",
                    position: "人事部经理",
                    sex: "男",
                    date: "2019年10月16日",
                    nativePlace: "广西",
                    phone: "12345678910",
                    email: "12345678@qq.com",
                },
                {
                    name: '宇哥',
                    jobNum: 20191016001,
                    department: "人事部",
                    position: "人事部经理",
                    sex: "男",
                    date: "2019年10月16日",
                    nativePlace: "广西",
                    phone: "12345678910",
                    email: "12345678@qq.com",
                },
                {
                    name: '宇哥',
                    jobNum: 20191016001,
                    department: "人事部",
                    position: "人事部经理",
                    sex: "男",
                    date: "2019年10月16日",
                    nativePlace: "广西",
                    phone: "12345678910",
                    email: "12345678@qq.com",
                },

                {
                    name: '宇哥',
                    jobNum: 20191016001,
                    department: "人事部",
                    position: "人事部经理",
                    sex: "男",
                    date: "2019年10月16日",
                    nativePlace: "广西",
                    phone: "12345678910",
                    email: "12345678@qq.com",
                },{
                    name: '宇哥',
                    jobNum: 20191016001,
                    department: "人事部",
                    position: "人事部经理",
                    sex: "男",
                    date: "2019年10月16日",
                    nativePlace: "广西",
                    phone: "12345678910",
                    email: "12345678@qq.com",
                },
                {
                    name: '宇哥',
                    jobNum: 20191016001,
                    department: "人事部",
                    position: "人事部经理",
                    sex: "男",
                    date: "2019年10月16日",
                    nativePlace: "广西",
                    phone: "12345678910",
                    email: "12345678@qq.com",
                },
                {
                    name: '宇哥',
                    jobNum: 20191016001,
                    department: "人事部",
                    position: "人事部经理",
                    sex: "男",
                    date: "2019年10月16日",
                    nativePlace: "广西",
                    phone: "12345678910",
                    email: "12345678@qq.com",
                },
                {
                    name: '宇哥',
                    jobNum: 20191016001,
                    department: "人事部",
                    position: "人事部经理",
                    sex: "男",
                    date: "2019年10月16日",
                    nativePlace: "广西",
                    phone: "12345678910",
                    email: "12345678@qq.com",
                },
            ],
        }
    }
}
</script>
