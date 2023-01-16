/* global kakao */
import React, { useCallback, useEffect, useState } from 'react'
import { Link, withRouter } from 'react-router-dom'
import qs from 'qs';
import axios from 'axios';
import LocalItem from './LocalItem';
const { kakao } = window;

const LocalList = ({ location }) => {
    const search = qs.parse(location.search, { ignoreQueryPrefix: true });
    const query = !search.query ? '인천 학익동' : search.query;
    const page = !(search.page) ? 1 : parseInt(search.page);
    const [locals, setLocals] = useState([]);
    const [loading, setLoading] = useState(false);
    const [is_end, setIs_end] = useState(false);

    const callAPI = useCallback(async () => {
        const url = `https://dapi.kakao.com/v2/local/search/keyword.json?size=5&query=${query}&page=${page}`;
        const config = {
            headers: { "Authorization": "KakaoAK 2469aaeb17ee099f8a89b6662cd32e66" }
        };
        setLoading(true);
        const result = await axios.get(url, config);
        setLoading(false);
        const documet = result.data.documents;
        const documents = page === 1 ? documet : locals.concat(documet);
        setLocals(documents);
        setIs_end(result.data.meta.is_end);
    }, [query, page]);

    const callMap = useCallback((x, y) => {
        let container = document.getElementById("map");
        let options = {
            center: new window.kakao.maps.LatLng(y, x),
            level: 3,
        };

        let map = new window.kakao.maps.Map(container, options);
        let markerPosition = new kakao.maps.LatLng(y, x);
        let marker = new kakao.maps.Marker({
            position: markerPosition
        });
        marker.setMap(map);
        console.log("loading kakaomap");
    }, []);

    useEffect(() => {
        callAPI();
    }, [query, page]);

    if (loading) {
        return (<h1>데이터를 불러오는 중</h1>);
    }

    return (
        <div>
            <h3>검색결과 : {query}</h3>
            <div id="map" style={{width:"500px", height:"400px", border : '1px solid gray', marginBottom : '10px'}}></div>
            {locals.map(local =>
                <LocalItem key={local.id} local={local} callMap={callMap}/>
            )}
            {is_end ||
                <Link to={`/local?query=${query}&page=${page+1}`}><span>더보기</span></Link>
            }
        </div>
    )
}

export default withRouter(LocalList)