import React, { useState } from 'react'
import Comp1 from './Comp1'
import axios from 'axios';

export default function App() {

    const [file, setFile] = useState()

    function handleChange(event) {
        setFile(event.target.files[0])
    }

    function handleSubmit(event) {
        event.preventDefault()
        const url = 'http://localhost:8080/api/files/single/base';
        const formData = new FormData();
        formData.append('file', file);
        // formData.append('fileName', file.name);
        const config = {
            headers: {
                'content-type': 'multipart/form-data',
            },
        };
        axios.post(url, formData, config).then((res) => {
            console.log(res.data);
        });
    }

    return (
        <div className='App'>
            <form onSubmit={handleSubmit}>
                <h1>React file upload</h1>
                <input type='file' onChange={handleChange} />
                <button type='submit'>upload</button>
            </form>
        </div>
    )
}
