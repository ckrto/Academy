import qs from 'qs';
import React from 'react'

const About = ( {location} ) => {
    console.log('location', location);
    const query = qs.parse (
        location.search, {ignoreQueryPrefix : true}
    );

    console.log(query);
    const page = parseInt(query.page);
    const detail = query.detail === 'true' ? true : false;
    const key = query.key;

    return (
        <div>
            <h1>소개</h1>
            <p>이 프로젝트는 라우터 예제입니다.</p>
            {detail && <p>key : {key}, page : {page}</p>}
        </div>
    )
}

export default About