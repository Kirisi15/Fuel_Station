import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./HomePageDesign.css";

const HomePageDesign = () => {
  return (
    <div>
      <div
        className="hero-section text-white text-center"
        style={{
          backgroundImage: "url('/image5.jpg')",
          backgroundSize: "cover",
          backgroundPosition: "center",
          height: "100vh",
        }}
      >
        
      </div>

      <div className="content-section py-5">
        <div className="container">
          <section className="news-section mb-5">
            <h2 className="text-center mb-4">Latest News</h2>
            <div className="row">
              {[1, 2, 3].map((item) => (
                <div className="col-md-4" key={item}>
                  <div className="card">
                    <img
                      src={`https://via.placeholder.com/300x200?text=News+${item}`}
                      className="card-img-top"
                      alt={`News ${item}`}
                    />
                    <div className="card-body">
                      <h5 className="card-title">News Title {item}</h5>
                      <p className="card-text">
                        A brief description of the news article goes here.
                      </p>
                      <a href="#" className="btn btn-theme">
                        Read More
                      </a>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </section>

          <section className="video-section mb-5">
            <h2 className="text-center mb-4">Latest Updates</h2>
            <div className="row justify-content-center">
              <div className="col-md-8">
                <div className="embed-responsive embed-responsive-16by9">
                  <iframe
                    className="embed-responsive-item"
                    src="https://www.youtube.com/embed/tgbNymZ7vqY"
                    allowFullScreen
                    title="Latest Updates Video"
                  ></iframe>
                </div>
              </div>
            </div>
          </section>

          <section className="gallery-section">
            <h2 className="text-center mb-4">Gallery</h2>
            <div className="row">
              {[1, 2, 3, 4].map((item) => (
                <div className="col-md-3" key={item}>
                  <img
                    src={`https://via.placeholder.com/200x150?text=Gallery+${item}`}
                    alt={`Gallery ${item}`}
                    className="img-fluid rounded mb-3"
                  />
                </div>
              ))}
            </div>
          </section>
        </div>
      </div>
    </div>
  );
};

export default HomePageDesign;