<template>
    <div>
        <Tabs type="card">
            <TabPane label="部门统计"><DeparStatistics></DeparStatistics></TabPane>
            <TabPane label="职位统计"><PositionStatistics></PositionStatistics></TabPane>
        </Tabs>
    </div>
</template>

<script>
    import DeparStatistics from './totalStatistics/DeparStatistics.vue'
    import PositionStatistics from './totalStatistics/PositionStatistics.vue'

    export default {
        components: {
            'DeparStatistics': DeparStatistics,
            'PositionStatistics': PositionStatistics,
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