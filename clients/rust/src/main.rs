use std::io::prelude::*;
use std::net::TcpStream;

fn main() -> std::io::Result<()> {
    let mut stream = TcpStream::connect("127.0.0.1:1978")?;

    // write buffered
    stream.write_all(b"Hello")?;
    // stream.write(&[1])?;
    stream.flush()?;
    stream.read(&mut [0; 128])?;
    Ok(())
} // the stream is closed here
