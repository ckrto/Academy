import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Card, Row, Button } from 'react-bootstrap';

const MoviePage = () => {
    const [movies, setMovies] = useState([]);

    const callMovies = async () => {
        const result = await axios.get('/api/crawl/cgv');
        setMovies(result.data);
    }

    useEffect(() => {
        callMovies();
    }, []);

    const onClick = (link) => {
        window.open(link, '_blank');
    }

    if (!movies) {
        return <h1>Loading......</h1>;
    }

    return (
        <div>
            <Row className='d-flex justify-content-center'>
                {movies.map(movie =>
                    <Card key={movie.link} style={{ width: "15rem", margin: 10, padding: 5 }}>
                        <Card.Img src={movie.image} />
                        <Card.Body>
                            <Card.Title className='ellipsis'>{movie.no} {movie.title}</Card.Title>
                            <a href={movie.link}><Button onClick={onClick}>예매하기</Button></a>
                        </Card.Body>
                    </Card>
                )}
            </Row>
        </div>
    )
}

export default MoviePage