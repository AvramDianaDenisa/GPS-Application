let map;
let markers;
//let myLatLng = {lat: 46.7693924, lng: 23.5902006};

function initializeMap() {
    map = new OpenLayers.Map("mapdiv");
    map.addLayer(new OpenLayers.Layer.OSM());

    let zoom = 10;
    let lonLat = getLonLat(myLatLng.lat, myLatLng.lng);

    markers = new OpenLayers.Layer.Markers("Markers");
    map.addLayer(markers);

    //markers.addMarker(new OpenLayers.Marker(lonLat));

    map.setCenter(lonLat, zoom);
}

function addStaticMarker() {
    let respData = localStorage.getItem('respData');
    let pos = getRandomPosition();
    let lonLat = getLonLat(pos.lat, pos.lng);
    markers.addMarker(new OpenLayers.Marker(lonLat))
}

function getRandomPosition() {
    let randLatLng = {
        lat: (myLatLng["lat"] + Math.floor(Math.random() * 5) + 1),
        lng: (myLatLng["lng"] + Math.floor(Math.random() * 5) + 1)
    };
    return randLatLng;
}

function getLonLat(lat, lng) {
    return new OpenLayers.LonLat(lng, lat)
        .transform(
            new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
            map.getProjectionObject() // to Spherical Mercator Projection
        );
}

function addMarkers() {
    getPositions();
    let respData = localStorage.getItem('respData');

    if (respData) {
        try {
            // Converteste șirul "[object Object], [object Object], ..." la un șir JSON valid
            respData = respData.replace(/\[object Object\]/g, '{}');

            // Parsează șirul JSON
            let positionsArray = JSON.parse('[' + respData + ']');

            for (let i = 0; i < positionsArray[0].length; i++) {
                let currentposition = positionsArray[0][i];
                let latitude = parseFloat(currentposition.latitude);
                let longitude = parseFloat(currentposition.longitude);
                let lonLat = getLonLat(latitude,longitude);
                markers.addMarker(new OpenLayers.Marker(lonLat));
            }
        } catch (error) {
            console.error("Eroare la parsarea JSON:", error);
        }
    }
    localStorage.removeItem('respData');

}