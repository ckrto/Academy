/* global kakao */
import React, { useEffect } from "react";
import qs from 'qs';

const { kakao } = window;

const Map = ( {location} ) => {
    const search = qs.parse (
        location.search, {ignoreQueryPrefix:true}
    );

    const x = search.x;
    const y = search.y;

    useEffect(() => {
        let container = document.getElementById("map");
        let options = {
            center: new window.kakao.maps.LatLng(y, x),
            level: 3,
        };

        let map = new window.kakao.maps.Map(container, options);
        let markerPosition = new kakao.maps.LatLng(y, x);
        let marker = new kakao.maps.Marker({
            position: markerPosition
        })
        marker.setMap(map);
        console.log("loading kakaomap");
    }, [x, y]);

    return (
        <div id="map" style={{width:"500px", height:"400px"}}></div>
  );
};

export default Map;