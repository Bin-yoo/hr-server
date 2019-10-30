<template>
    <div>
        <Tabs type="card">
            <TabPane label="部门统计"><DeparStatistics></DeparStatistics></TabPane>
            <TabPane label="职位统计">标签二的内容</TabPane>
            <TabPane label="员工生日">标签三的内容</TabPane>
        </Tabs>
    </div>
</template>

<script>
    import DeparStatistics from './totalStatistics/DeparStatistics.vue'

    export default {
        components: {
            'DeparStatistics': DeparStatistics,
        },
        data() {
            return {

            }
        },
        methods: {

        },
    }
</script>

<style scoped>
    
</style>