import "./App.css";
import { Button, Col, Container, Form, ListGroup, Row } from "react-bootstrap";
import { useEffect, useState } from "react";

function App() {
  const [keyword, setKeyword] = useState("");
  const [books, setBooks] = useState(null);
  const [authors, setAuthors] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8080/api/books", {
      headers: {
        "Content-Type": "application/json",
      },
      method: "GET",
    })
      .then((response) => {
        if (response.status === 200) {
          console.log("Response here:");
          console.log(response);
          return response.json();
        }
      })
      .then((response) => {
        console.log("Books here:");
        console.log(response);
        setBooks(response);
      });
    fetch("http://localhost:8080/api/authors", {
      headers: {
        "Content-Type": "application/json",
      },
      method: "GET",
    })
      .then((response) => {
        if (response.status === 200) {
          console.log("Response here:");
          console.log(response);
          return response.json();
        }
      })
      .then((response) => {
        console.log("Authors here:");
        console.log(response);
        setAuthors(response);
      });
  }, []);

  function sendKeyword() {
    const formData = {
      keyword: keyword,
    };

    fetch("http://localhost:8080/api/search", {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify(formData),
    })
      .then((response) => {
        if (response.status === 200) {
          console.log("Response here:");
          console.log(response);
          return response.json();
        }
      })
      .then((response) => {
        console.log("Books here:");
        console.log(response);
        setBooks(response);
      });
  }

  return (
    <div className="App">
      <Container>
        <Row>
          <Col md={8}>
            <Form>
              <p className="text-start m-0 pt-4">
                <Form.Label>Search</Form.Label>
              </p>
              <Row>
                <Col md={6} className="px-2">
                  <Form.Group
                    className="mb-3"
                    controlId="exampleForm.ControlInput1"
                  >
                    <Form.Control
                      type="text"
                      placeholder="Search a keyword here"
                      onChange={(event) => {
                        setKeyword(event.target.value);
                        sendKeyword();
                      }}
                    />
                  </Form.Group>
                </Col>
                <Col md={2}>
                  <Button
                    variant="primary"
                    type="button"
                    className="d-inline w-100"
                    onClick={() => sendKeyword()}
                  >
                    Submit
                  </Button>
                </Col>
              </Row>
            </Form>
          </Col>
        </Row>

        <Row>
          <Col md={8}>
            <h4 className="text-start">Book List:</h4>
            <ListGroup variant="flush">
              {books ? (
                books.map((book) => (
                  <ListGroup.Item className="text-start">
                    <h5>{book.title}</h5>
                    <p>{authors.map((author) => author.name)}</p>
                  </ListGroup.Item>
                ))
              ) : (
                <></>
              )}
            </ListGroup>
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default App;
