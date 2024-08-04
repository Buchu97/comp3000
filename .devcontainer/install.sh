apt-get update
apt-get install -y gcc git cmake
apt-get install -y ruby ruby-dev
apt-get install -y swi-prolog
apt-get install -y scala
apt-get install -y clojure
apt-get install -y ghc
# io is a bit of a hassle
git clone --recursive https://github.com/IoLanguage/io.git
cd io
sed -i.bak -e '42d' CMakeLists.txt # for some reason line 42 asks for x86 instructions that might not be there on all computers
mkdir build
cd build
cmake ..
make
make install
cd ..
cd ..
rm -Rf io
