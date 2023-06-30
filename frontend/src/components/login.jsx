import { useState } from "react";
import { Container, Form } from "react-bootstrap";
import { getEmployeeByUsername, handleLogin } from "../api/auth";
import { useNavigate } from "react-router-dom";

export default function LoginPage(props) {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        const user = {
            username: username,
            password: password
        };

        console.log(user);


        const response = await handleLogin(user);

        sessionStorage.setItem("token", response.data.accessToken);
        sessionStorage.setItem("username", username);

        const userData = await getEmployeeByUsername(username);

        console.log(response);

        navigate(`/home/${userData.data.role.name.toLowerCase()}`);
    }

    return (
        <Container className="pageContainer">
            <h1>Login</h1>
            <Form onSubmit={handleSubmit} className="loginForm">
                <Form.Group className="mb-3" controlId="username">
                    <Form.Label>Username</Form.Label>
                    <Form.Control onChange={(e) => setUsername(e.target.value)} type="text" placeholder="Enter username" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="password">
                    <Form.Label>Password</Form.Label>
                    <Form.Control onChange={(e) => setPassword(e.target.value)} type="password" placeholder="Enter password" />
                </Form.Group>
                <button className="rsBtn" type="submit">Login</button>
                <button onClick={props.switch} className="mx-3 rsBtn" type="button">Sign Up</button>
            </Form>
        </Container>
    )
}