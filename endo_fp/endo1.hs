module Main where

import System.IO as IO
import qualified Data.ByteString.Lazy as L
import qualified Data.ByteString.Lazy.Char8 as C

main :: IO ()
main = do
  contents <- L.getContents
  C.putStr (translate contents)

translate :: L.ByteString -> L.ByteString
translate string = translate' (L.take 7 string) (L.drop 7 string)

translate' :: L.ByteString -> L.ByteString -> L.ByteString
translate' string rest 
    | string == C.pack "PIPIIIC" = L.append (C.pack "black\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PIPIIIP" = L.append (C.pack "red\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PIPIICC" = L.append (C.pack "green\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PIPIICF" = L.append (C.pack "yellow\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PIPIICP" = L.append (C.pack "blue\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PIPIIFC" = L.append (C.pack "magenta\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PIPIIFF" = L.append (C.pack "cyan\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PIPIIPC" = L.append (C.pack "white\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PIPIIPF" = L.append (C.pack "trans\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PIPIIPP" = L.append (C.pack "opaque\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PIIPICP" = L.append (C.pack "empty\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PIIIIIP" = L.append (C.pack "move\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PCCCCCP" = L.append (C.pack "ccw\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PFFFFFP" = L.append (C.pack "cw\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PCCIFFP" = L.append (C.pack "mark\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PFFICCP" = L.append (C.pack "line\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PIIPIIP" = L.append (C.pack "fill\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PCCPFFP" = L.append (C.pack "addBitmap\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PFFPCCP" = L.append (C.pack "compose\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | string == C.pack "PFFICCF" = L.append (C.pack "clip\n") (translate' (L.take 7 rest) (L.drop 7 rest))
    | L.length string == 0 = L.empty
    | L.length string < 7 = C.pack "SHORT\n"
    | otherwise = L.append (C.pack "UNKNOWN\n") (translate' (L.take 7 rest) (L.drop 7 rest))