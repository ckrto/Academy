import axios from 'axios';
import React, { useEffect, useRef, useState } from 'react'
import Chart from 'react-google-charts';
import { Link } from 'react-router-dom';
import qs from 'qs';

export const options = {
    chart: {
        title: "Company Performance",
        subtitle: "Sales, Expenses, and Profit: 2014-2017",
    },
};

const CustomerChart = ({ location }) => {
    const search = qs.parse(location.search, { ignoreQueryPrefix: true });
    const type = search.type;
    const [data, setData] = useState([]);
    const title = useRef('직업별인원수');
    const chartType = useRef('Bar');

    const options = {
        chart: {
            title: title.current
        },
        title: title.current
    };

    const callAPI = async () => {
        let result = null;
        let array = [];
        switch (type) {
            case '1':
                result = await axios.get(`/customers/chart/${type}`);
                array = changeData(result.data);
                break;
            case '2':
                result = await axios.get(`/customers/chart/${type}`);
                array = changeData2(result.data);
                break;
            case '3':
                result = await axios.get(`/customers/chart/${type}`);
                array = changeData3(result.data);
                break;
            case '4':
                result = await axios.get('/customers/chart/job_gender');
                array = changeData4(result.data);
                break;
        }
        setData(array);
    }

    const changeData = (result) => {
        title.current = '직업별인원수';
        chartType.current = 'Bar';
        let array = [];
        array.push(['직업명', '인원수']);
        result.forEach(item => {
            array.push([item.job, item.count]);
        });
        return array;
    }

    const changeData2 = (result) => {
        title.current = '성별인원수';
        chartType.current = 'PieChart';
        let array = [];
        array.push(['성별', '인원수']);
        result.forEach(item => {
            array.push([item.gender, item.count]);
        });
        return array;
    }

    const changeData3 = (result) => {
        title.current = '2022 월별 인원수';
        chartType.current = 'LineChart';
        let array = [];
        array.push(['2022년', '인원수']);
        result.forEach(item => {
            array.push([item.month, item.count]);
        });
        return array;
    }

    const changeData4 = (result) => {
        title.current = '직업별/성별인원수';
        chartType.current = 'Bar';
        let array = [];
        array.push(['직업명', '남', '여']);
        result.forEach(item => {
            array.push([item.dis_job, item.dis_gender, item.count]);
        });
        return array;
    }

    useEffect(() => {
        callAPI();
    }, [type]);

    return (
        <div>
            <div className='subMenu'>
                <Link to="/chart?type=1">직업별인원수</Link>
                <Link to="/chart?type=2">성별인원수</Link>
                <Link to="/chart?type=3">년도별인원수</Link>
                <Link to="/chart?type=4">직업별/성별인원수</Link>
            </div>
            <hr />
            <Chart
                chartType={chartType.current}
                width="100%"
                height="400px"
                data={data}
                options={options}
            />
        </div>
    )
}

export default CustomerChart