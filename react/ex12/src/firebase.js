import { initializeApp } from "firebase/app";

const firebaseConfig = {
    apiKey: "AIzaSyCx2CDLOKXuZI5JnNTKWBVQuzdciv30E_0",
    authDomain: "fir-8275f.firebaseapp.com",
    databaseURL : "https://fir-8275f-default-rtdb.firebaseio.com",
    projectId: "fir-8275f",
    storageBucket: "fir-8275f.appspot.com",
    messagingSenderId: "441962353048",
    appId: "1:441962353048:web:de46df31cfb2c004bc1bed",
    measurementId: "G-CLM7DL1GNW"
};

export const app = initializeApp(firebaseConfig);